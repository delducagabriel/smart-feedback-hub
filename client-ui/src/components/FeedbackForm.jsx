import { useState } from 'react';

// recebe o onAddFeedback como prop do componente pai para adicionar o feedback
function FeedbackForm({ onAddFeedback }) {
    const [text, setText] = useState(''); // estado local apenas para controlar o valor do input

    const handleSubmit = (e) => {
        if (e && e.preventDefault) e.preventDefault(); // previne o comportamento padrão do form
        if (!text.trim()) return; // não envia se o texto estiver vazio ou só com espaços
        onAddFeedback(text); // chama a função do pai para adicionar o feedback
        setText(''); // limpa o input após enviar
    }

    const handleKeyDown = (e) => {
        if (e.key === 'Enter' && !e.shiftKey) { // permite enviar com Enter, mas não com Shift+Enter
            e.preventDefault();
            handleSubmit(e);
        }
    }

    return (
        <form onSubmit={handleSubmit} className="feedback-form">
            <textarea
                id="feedbackInput"
                name="feedbackInput"
                value={text}
                onChange={(e) => setText(e.target.value)}
                onKeyDown={handleKeyDown}
                placeholder="Escreva seu feedback aqui..."
                rows="4"
                required
            />
            <button type="submit">Analisar Sentimento</button>
        </form>
    )
}

export default FeedbackForm;

