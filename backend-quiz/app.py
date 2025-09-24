import pickle
import pandas as pd
from flask import Flask, request, jsonify

# AVISO: PARA INICIAR O SERVIDOR, BASTA DIGITAR "python app.py" NO GIT BASH DO PROJETO, ENTÃO
# O SERVIDOR SERÁ INICIADO.

# 1. Inicializar a aplicação Flask
app = Flask(__name__)

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

    # 4. Receber os dados enviados pelo front-end (em formato JSON)
    data = request.get_json()

    # Exemplo de como o front-end deve enviar os dados:
    # {
    #   "respostas": { "Q1": "A", "Q2": "A", ..., "Q7A": "A", ... }
    # }

    respostas_usuario = data.get('respostas', {})

    # 5. Montar o DataFrame para a previsão, preenchendo as colunas não respondidas com "NULO"
    dados_para_previsao = {}
    for col in features_columns:
        dados_para_previsao[col] = respostas_usuario.get(col, "NULO")

    df_pessoa = pd.DataFrame([dados_para_previsao], columns=features_columns)

    # 6. Fazer o pré-processamento e a previsão (exatamente como nas simulações)
    try:
        # Aplicar o OneHotEncoding
        pessoa_transformada = ohe.transform(df_pessoa)
        
        # Fazer a previsão com o modelo
        previsao_codificada = model.predict(pessoa_transformada)
        
        # Decodificar o resultado para o nome do gênero
        resultado_final = le.inverse_transform(previsao_codificada)
        
        # 7. Enviar a resposta de volta para o front-end em formato JSON
        return jsonify({'genero_previsto': resultado_final[0]})

    except Exception as e:
        # Se algo der errado, envie uma mensagem de erro
        return jsonify({'error': str(e)}), 400

# 8. Bloco para rodar a aplicação
if __name__ == '__main__':
    # O host='0.0.0.0' permite que o app seja acessível na sua rede local (útil para testes com celular)
    app.run(host='0.0.0.0', port=5000, debug=True)