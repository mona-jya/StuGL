<template>
  <div class="login">
    <Login_Table ></Login_Table>
  </div>
<video src="../assets/mp4/output.mp4" style="width: 100%;height: 100%;object-fit: cover;position: absolute;top: 0;left: 0;" autoplay="autoplay" loop="loop" muted="muted"></video>
  <div class="zfj" @click="repeatable">
    <IconZFJ ></IconZFJ>
  </div>
  <div class="ly" @click="repeatable2">
    <IconLY/>
  </div>
  <!-- <br> -->
</template>

<script setup lang="ts" name="HomeView">



import { gsap } from 'gsap'
import { onMounted } from 'vue'
import { RouterLink, RouterView } from 'vue-router'
import IconZFJ from '../components/icons/IconZFJ.vue'
import IconLY from '../components/icons/IconLY.vue'
import { CustomEase } from "gsap/CustomEase";
import Login_Table from '../components/Login_Table.vue'
onMounted(()=>{

  gsap.to('.login',{
    x:700,duration:4
  }

  )

  gsap.fromTo('.zfj',{
    x:400,yPercent:30,
  },{x:-300,duration:3,rotation: 360,y:-50,scale:1.2}
  )
  gsap.registerPlugin(CustomEase) 

gsap.to(".ly", {

  keyframes: {
    "0%": { x: -100,rotation:10 },
    "10%": { x: -200,rotation:-10  },
    "20%": {  x: -300,rotation:10 },
    "30%": {  x: -400,rotation:-10 },
    "40%": { x: -500,rotation:10  },
    "50%": { x: -600,rotation:-10 },
    "60%": { x: -700,rotation:10 },
    "70%": { x: -800,rotation:-10 },
  ease: CustomEase.create("custom", "M0,0,C0,0,1,1,1,1"),
}
,duration:6
})
})
let times=1
function repeatable(){
 times+=1
  gsap.fromTo('.zfj',{
    x:400,yPercent:30,
  },{x:-300,duration:3,rotation: 360*times,y:-90,scale:1.2}
  )
}
function repeatable2(){
  gsap.registerPlugin(CustomEase) 

gsap.to(".ly", {

  keyframes: {
    "0%": { x: -100,rotation:10 },
    "10%": { x: -200,rotation:-10  },
    "20%": {  x: -300,rotation:10 },
    "30%": {  x: -400,rotation:-10 },
    "40%": { x: -500,rotation:10  },
    "50%": { x: -600,rotation:-10 },
    "60%": { x: -700,rotation:10 },
    "70%": { x: -800,rotation:-10 },
  ease: CustomEase.create("custom", "M0,0,C0,0,1,1,1,1"),
}
,duration:6
})
}
</script>
<style scoped>
.login{

  position: absolute; /* 设置为绝对定位 */
  right: 1500px; /* 距离父元素右侧0 */
  bottom: 500px; /* 距离父元素底部0 */
  /* 其他样式，如宽度、高度、背景色等 */
  width: 300px; /* 例如，宽度为100像素 */
  height: 300px; /* 例如，高度为100像素 */
  z-index: 9999;
}

.ly {  
  transform: rotate(-20deg);  
  position: absolute; /* 设置为绝对定位 */  
  right: 100px; /* 距离父元素右侧0 */  
  bottom: 100px; /* 距离父元素底部0 */  
  /* 其他样式，如宽度、高度、背景色等 */  
  width: 300px; /* 例如，宽度为100像素 */  
  height: 300px; /* 例如，高度为100像素 */  

}
.zfj {
  transform: rotate(-20deg);
  position: absolute; /* 设置为绝对定位 */
  right: 600px; /* 距离父元素右侧0 */
  bottom: 300px; /* 距离父元素底部0 */
  /* 其他样式，如宽度、高度、背景色等 */
  width: 300px; /* 例如，宽度为100像素 */
  height: 300px; /* 例如，高度为100像素 */

}

</style>
