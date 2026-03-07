import { useState, useEffect, use } from 'react';
import api from './services/api'; // cliente axios configurado
import FeedbackForm from './components/FeedbackForm';
import FeedbackList from './components/FeedbackList';
import './index.css';

function App() {
    // estado global que guarda a lista de feedbacks
    const [feedbacks, setFeedbacks] = useState([]);

    // useEffect roda uma vez quando a tela é carregada para buscar os feedbacks existentes
    useEffect(() => {
      loadFeedbacks();
    }, []);

    const loadFeedbacks = async () => {
        try {
            const response = await api.get('/feedbacks');
            setFeedbacks(response.data);
        } catch (error) {
            console.error('Erro ao carregar feedbacks:', error);
        }
    }

    const handleAddFeedback = async (text) => {
        console.log("Texto recebido, enviando para API")

        try {
            const response = await api.post('/feedbacks', { text }); // envia o texto para API, que vai processar e retornar o feedback com sentimento e score
            console.log("O servidor respondeu", response.data);
            setFeedbacks([response.data, ...feedbacks]);
        } catch (error) {
            console.error("Erro ao adicionar feedback:", error);
            alert('Erro ao conectar com o servidor. Verifique se a API está rodando.');
        }
    }

    return (
        <div className="app-container">
            <header className="app-header">
                <h1>Smart Feedback Hub</h1>
                <p>Análise de sentimentos em tempo real</p>
            </header>

            <main>
                <FeedbackForm onAddFeedback={handleAddFeedback} />
                <FeedbackList feedbacks={feedbacks} />
            </main>
        </div>
    )
}

export default App;