# import do flask para API web e lidar com requisições HTTP (request, jsonify) e do TextBlob para análise de sentimento (NLP)
from flask import Flask, request, jsonify
from textblob import TextBlob

# inicia a aplicação Flask
app = Flask(__name__)

# define a rota '/analyze' para lidar com requisições POST, onde os usuários enviarão o texto a ser analisado
@app.route('/analyze', methods=['POST'])
def analyze_sentiment():
    
    # endpoint que recebe um JSON com o texto a ser analisado, realiza a análise de sentimento usando TextBlob e retorna o resultado
    data = request.get_json()  # obtém os dados JSON da requisição

    if not data or 'text' not in data:
        return jsonify({'error': 'O campo "text" é obrigatório.'}), 400  # retorna um erro se o campo "text" não estiver presente
    
    text = data['text']

    # realiza a análise de sentimento usando TextBlob
    # para este MVP, o TextBlob puro funciona melhor com textos em ingls, para uma versão pt-br, trocaríamos a biblioteca aqui dentro sem afetar o resto do sistema
    blob = TextBlob(text)
    sentiment = blob.sentiment.polarity  # obtém a polaridade do sentimento (-1 a 1)

    if polarity > 0.1:
        sentiment_label = 'positivo'
    elif polarity < -0.1:
        sentiment_label = 'negativo'
    else:
        sentiment_label = 'neutro'

    # formata a resposta com o texto original, o rótulo do sentimento e a pontuação de polaridade, arredondada para 2 casas decimais
    response = {
        'text': text,
        'sentiment': sentiment_label,
        'score': round(polarity, 2) 
    }

    return jsonify(response)  # retorna a resposta em formato JSON

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)  # inicia o servidor Flask na porta 5000, acessível de qualquer IP, com modo de depuração ativado

"""
Validação de Entrada: o bloco if not data protege a API, se o core backend ou o client ui enviarem dados quebrados, o microserviço não quebra, ele respnde com um erro '400 Bad Request'
Abstração da IA: o TextBlob é usado para análise de sentimento, mas a estrutura do código permite que a biblioteca seja facilmente substituída por outra solução de NLP no futuro, sem afetar o restante do sistema, o que é importante para escalabilidade e manutenção a longo prazo
Padrão de Resposta: a resposta JSON inclui o texto original, o rótulo do sentimento e a pontuação de polaridade, o que facilita a integração com outros serviços ou interfaces de usuário que possam consumir essa API
"""
