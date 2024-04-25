<template>
 
    <el-form :model="form" label-width="auto" style="max-width: 900px">
      <el-text class="title" type="primary" size="large">欢迎来到mona小站</el-text>
      <br>
      <el-form-item label="用户名" class="item">
        <el-input v-model="form.name" />
      </el-form-item>
      <el-form-item label="密码" class="item">
        <el-input  v-model="form.password"
    style="width: 240px"
    type="password"
    placeholder="Please input password"
    show-password/>
      </el-form-item>

      <el-form-item class="item">
        <el-button type="primary" @click="onLogin">登录</el-button>
        
        <el-button type="success" @click="onSubmit">注册</el-button>
        <el-button type="Info">取消</el-button>
      </el-form-item>
    </el-form>

    
  </template>
  
  <script lang="ts" setup name="Login_Table">

  import md5 from 'js-md5'
  import {onMounted, reactive } from 'vue'
  import {useRouter} from 'vue-router'
  import getAxios from '@/tools/axios'
  const router=useRouter()
  // do not use same name with ref
  const form = reactive({
    name: 'mona',
    password:'password'
  })

  onMounted(()=>{
   localStorage.removeItem('token')
  })
  
  function onSubmit(){
    router.push('/submit');
    // console.log(stuList)
  }
  let stuList:any;
  const onLogin =async () => {

    form.password=md5(form.password);
    getAxios().axiosInstance.post("/user/login",form).then((response)=>{
      console.log(response.data==='error 500')
      if(response.data==='error 500'){
        alert("没有这个用户，请注册。")
      }else{
        localStorage.setItem("token",response.data)
        localStorage.removeItem("userName")
        localStorage.setItem("userName",form.name)
        router.push("/manage");
      }

    })


  }

  </script>
  <style scoped>
 .mx-1{
  text-align: center;
 }
 .item{
  display: flex;
  justify-content: center;
  align-items: center;
  
 }
 .title{
  display: flex;  
  justify-content: center; /* 水平居中 */  
  align-items: center; /* 垂直居中 */  
  text-align: center;
  font-family: sans-serif; /* 替换为你的字体名称 */  
  font-weight: bold; /* 设置字体粗细为加粗 */  
 }
 
  
/* 可选：设置表单的背景色（如果需要的话） */  
.el-form {  
/* 设置背景图片 */  
background-image: url('/src/assets/img/3.jpg');  
  /* 确保背景图片覆盖整个容器 */  
  background-size: cover;  
  /* 图片不重复 */  
  background-repeat: no-repeat;  
  /* 图片位置，这里设置为居中 */  
  background-position: center;  
  /* 其他样式，如高度、内边距等 */  
 /* 假设你想让表单占据整个视口高度 */  
  background-color: rgba(255, 255, 255, 0.8); /* 半透明背景，增加可读性 */  
box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); 
  border-radius: 10px; /* 添加圆角 */  
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1); /* 添加阴影效果 */  
  padding: 20px; /* 添加内边距 */  
}  
  </style>
  