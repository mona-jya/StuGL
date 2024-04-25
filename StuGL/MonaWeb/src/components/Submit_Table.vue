<template>
 <video src="../assets/mp4/submit.mp4" style="width: 100%;height: 100%;object-fit: cover;position: absolute;top: 0;left: 0;" autoplay="autoplay" loop="loop" muted="muted"></video>
  <div class="submit">
    <el-form :model="form" label-width="auto" style="max-width: 900px">
      <el-text class="title" type="primary" size="large">欢迎注册mona小站</el-text>
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
        <el-button type="success" @click="onSubmit">注册</el-button>

      </el-form-item>
    </el-form>
  </div>



    
  </template>
  
  <script lang="ts" setup name="Login_Table">
  import { reactive } from 'vue'
  import getAxios from "@/tools/axios";
  import router from "@/router";
  import md5 from 'js-md5'


  // do not use same name with ref
  const form = reactive({
    name: 'mona',
    password:'password'
  })
  
  const onSubmit =async () => {
    form.password=  md5(form.password);
    console.log(form)
    getAxios().axiosInstance.post("/addUser",form).then((response)=>{
      alert("注册成功。")
      router.push("/login");
    })
    console.log('submit!')
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
  z-index: 99;
/* 设置背景图片 */  
background-image: url('../assets/img/19.jpg');  
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
 .submit{

   position: absolute; /* 设置为绝对定位 */
   right: 1000px; /* 距离父元素右侧0 */
   bottom: 500px; /* 距离父元素底部0 */
   /* 其他样式，如宽度、高度、背景色等 */
   width: 300px; /* 例如，宽度为100像素 */
   height: 300px; /* 例如，高度为100像素 */
   z-index: 9999;
 }
  </style>
  