import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api',
});

export default api;

/*
Axios foi escolhido ao invés do fetch porque permite criar uma 'instância' pré-configurada, facilitando a reutilização e a manutenção do código. 
Importei a lib, criei uma instância com a URL base da API e exportei essa instância para ser usada em outros arquivos.
*/