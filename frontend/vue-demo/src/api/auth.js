import axios from 'axios'

const apiClient = axios.create({
  baseURL: 'http://localhost:8080/api',
  headers: {
    'Content-Type': 'application/json'
  }
})

export default {
  register(user) {
    return apiClient.post('/register', user)
  },
  login(credentials) {
    return apiClient.post('/login', credentials)
  },
  getUserInfo(username) {
    return apiClient.get(`/user/${username}`)
  }
}