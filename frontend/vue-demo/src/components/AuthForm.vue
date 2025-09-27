<template>
  <div class="auth-container">
    <h2>{{ isLoginMode ? '用户登录' : '用户注册' }}</h2>
    
    <form @submit.prevent="handleSubmit">
      <div class="form-group">
        <label>用户名</label>
        <input type="text" v-model="form.username" required>
      </div>
      
      <div class="form-group">
        <label>密码</label>
        <input type="password" v-model="form.password" required>
      </div>
      
      <div v-if="!isLoginMode" class="form-group">
        <label>邮箱</label>
        <input type="email" v-model="form.email" required>
      </div>
      
      <button type="submit" class="submit-btn">
        {{ isLoginMode ? '登录' : '注册' }}
      </button>
      
      <button type="button" class="switch-btn" @click="toggleMode">
        {{ isLoginMode ? '没有账号？注册' : '已有账号？登录' }}
      </button>
      
      <div v-if="message" class="message">
        {{ message }}
      </div>
    </form>
    
    <div v-if="userInfo" class="user-info">
      <h3>用户信息</h3>
      <p><strong>用户名:</strong> {{ userInfo.username }}</p>
      <p><strong>邮箱:</strong> {{ userInfo.email }}</p>
    </div>
  </div>
</template>

<script>
import authApi from '@/api/auth'

export default {
  data() {
    return {
      isLoginMode: false,
      form: {
        username: '',
        password: '',
        email: ''
      },
      message: '',
      userInfo: null
    }
  },
  methods: {
    toggleMode() {
      this.isLoginMode = !this.isLoginMode
      this.message = ''
      this.form = { username: '', password: '', email: '' }
    },
    async handleSubmit() {
      this.message = ''
      
      try {
        if (this.isLoginMode) {
          // 登录
          const response = await authApi.login({
            username: this.form.username,
            password: this.form.password
          })
          
          this.message = response.data
          
          // 获取用户信息
          const userResponse = await authApi.getUserInfo(this.form.username)
          this.userInfo = userResponse.data
        } else {
          // 注册
          const response = await authApi.register(this.form)
          this.message = response.data
          this.userInfo = this.form
        }
      } catch (error) {
        this.message = error.response?.data || '请求失败'
      }
    }
  }
}
</script>

<style scoped>
.auth-container {
  max-width: 400px;
  margin: 50px auto;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 8px;
  background-color: #f9f9f9;
}

h2 {
  text-align: center;
  margin-bottom: 20px;
  color: #333;
}

.form-group {
  margin-bottom: 15px;
}

label {
  display: block;
  margin-bottom: 5px;
  font-weight: bold;
}

input {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.submit-btn {
  width: 100%;
  padding: 10px;
  background-color: #4CAF50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;
  margin-bottom: 10px;
}

.switch-btn {
  width: 100%;
  padding: 10px;
  background-color: #f0f0f0;
  border: 1px solid #ddd;
  border-radius: 4px;
  cursor: pointer;
  font-size: 14px;
}

.message {
  margin-top: 15px;
  padding: 10px;
  background-color: #e9f7e9;
  border: 1px solid #c3e6c3;
  border-radius: 4px;
  text-align: center;
}

.user-info {
  margin-top: 20px;
  padding: 15px;
  background-color: #fff;
  border: 1px solid #ddd;
  border-radius: 4px;
}

.user-info h3 {
  margin-top: 0;
  border-bottom: 1px solid #eee;
  padding-bottom: 10px;
}
</style>