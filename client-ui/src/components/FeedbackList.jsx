function FeedbackList({ feedbacks }) {
    // se a lista estiver vazia, mostra uma mensagem de que não há feedbacks cadastrados
    if (feedbacks.length === 0) {
        return <p className="empty-message">Nenhum feedback cadastrado.</p>;
    }

    // função auxiliar pra definir a classe CSS com base no sentimento
    const getSentimentClass = (sentiment) => {
        if (!sentiment) return 'sentiment-neutral';

        const normalizedSentiment = sentiment.toUpperCase();
        if (normalizedSentiment === 'POSITIVO') return 'sentiment-positive';
        if (normalizedSentiment === 'NEGATIVO') return 'sentiment-negative';
        return 'sentiment-neutral';
    }

    return (
        <div className="feedback-list">
            { /* o .map() é usado para percorrer a lista e criar um card para cada item */ }
            {feedbacks.map((fb) => (
                <div key={fb.id} className={`feedback-card ${getSentimentClass(fb.sentiment)}`}>
                    <p className="feedback-text">{fb.text}</p>
                    <div className="feedback-footer">
                        <span className="sentiment-bagde">{fb.sentiment ? fb.sentiment.toUpperCase() : 'NEUTRO'}</span>
                        <span className="score-badge">Score: {fb.score}</span>
                    </div>
                </div>
            ))}
        </div>
    );
}

export default FeedbackList;    

