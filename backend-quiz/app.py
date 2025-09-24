import pickle
import pandas as pd
from flask import Flask, request, jsonify
from flask_cors import CORS

# 1. Inicializar a aplicação Flask
app = Flask(__name__)
CORS(app)  # <- tem que vir depois da criação do app

# 2. Carregar os modelos e encoders (faça isso apenas uma vez, quando a aplicação inicia)
try:
    with open('soundmatch_ml_model.pkl', 'rb') as f_model:
        model = pickle.load(f_model)
    with open('OHE.pkl', 'rb') as f_ohe:
        ohe = pickle.load(f_ohe)
    with open('LE.pkl', 'rb') as f_le:
        le = pickle.load(f_le)
    print("Modelos e encoders carregados com sucesso!")
except FileNotFoundError:
    print("Erro: Arquivos de modelo (.pkl) não encontrados. Verifique os nomes e o caminho.")
    model, ohe, le = None, None, None

# A lista de todas as colunas de features na ordem correta que o modelo espera
features_columns = ['Q1', 'Q2', 'Q3', 'Q4', 'Q5', 'Q6', 'Q7A', 'Q8A', 'Q9A', 'Q10A',
                    'Q7B', 'Q8B1', 'Q9B1', 'Q10B1', 'Q8B2', 'Q9B2', 'Q10B2']

# 3. Criar o "endpoint" da API que receberá as respostas e retornará a previsão
@app.route('/predict', methods=['POST'])
def predict():
    if not all([model, ohe, le]):
        return jsonify({"error": "Modelos não carregados. Verifique o servidor."}), 500

    data = request.get_json()

    # --- ADICIONE ESTA LINHA PARA VER OS DADOS RECEBIDOS ---
    print(f"DADOS RECEBIDOS DO APP: {data}")

    respostas_usuario = data.get('respostas', {})

    # Monta o DataFrame
    dados_para_previsao = {col: respostas_usuario.get(col, "NULO") for col in features_columns}
    df_pessoa = pd.DataFrame([dados_para_previsao], columns=features_columns)

    try:
        pessoa_transformada = ohe.transform(df_pessoa)
        previsao_codificada = model.predict(pessoa_transformada)
        resultado_final = le.inverse_transform(previsao_codificada)

        return jsonify({'genero_previsto': resultado_final[0]})
    except Exception as e:
        return jsonify({'error': str(e)}), 400

# 8. Bloco para rodar a aplicação
if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
