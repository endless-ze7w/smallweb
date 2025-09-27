<template>
  <div id="app">
    <header>
      <h1>用户注册登录系统</h1>
    </header>
    
    <main>
      <AuthForm />
    </main>
    
    <footer>
      <p>Vue + Spring Boot 简单示例</p>
    </footer>
  </div>
</template>

<script>
import AuthForm from './components/AuthForm.vue'

export default {
  components: {
    AuthForm
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: Arial, sans-serif;
  line-height: 1.6;
  background-color: #f5f5f5;
}

#app {
  display: flex;
  flex-direction: column;
  min-height: 100vh;
}

header {
  background-color: #2c3e50;
  color: white;
  padding: 20px;
  text-align: center;
}

main {
  flex: 1;
  padding: 20px;
  display: flex;
  justify-content: center;
  align-items: center;
}

footer {
  background-color: #2c3e50;
  color: white;
  text-align: center;
  padding: 10px;
}
</style>