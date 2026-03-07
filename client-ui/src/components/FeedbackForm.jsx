import { useState } from 'react';

// recebe o onAddFeedback como prop do componente pai para adicionar o feedback
function FeedbackForm({ onAddFeedback }) {
    const [text, setText] = useState(''); // estado local apenas para controlar o valor do input

    const handleSubmit = (e) => {
        e.preventDefault(); // evita que a página seja recarregada ao enviar o formulário

        if (!text.trim()) return; // evita enviar feedbacks vazios ou apenas com espaços

        console.log("Formulário interceptado, texto:", text);

        onAddFeedback(text); // chama a função que vai enviar para a API
        setText(''); // limpa o campo de texto após enviar
    }

    return (
        <form onSubmit={handleSubmit} className="feedback-form">
            <textarea
                id="feedbackInput"
                name="feedbackInput"
                value={text}
                onChange={(e) => setText(e.target.value)}
                placeholder="Escreva seu feedback aqui..."
                rows="4"
                required
            />
            <button type="submit">Analisar Sentimento</button>
        </form>
    )
}

export default FeedbackForm;

