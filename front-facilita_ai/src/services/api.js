// src/api.js
import axios from 'axios';

const API_URL = 'http://localhost:8080/api'; // Substitua pelo URL do seu back-end

export const login = async (email, password) => {
    try {
        const response = await axios.post(`${API_URL}/login`, { email, password });
        return response.data;
    } catch (error) {
        console.error('Erro ao fazer login:', error);
        throw error;
    }
};