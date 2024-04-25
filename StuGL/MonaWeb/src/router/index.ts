import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import SubmitView from '../components/Submit_Table.vue'
import ManageStuView from "@/components/ManageStuView.vue";
import ManageUserView from "@/components/ManageUserView.vue";
import ManageView from "@/views/ManageView.vue";
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/login',
      name: 'home_login',
      component: HomeView
    },
    {
      path: '/submit',
      name: 'submit',
      component: SubmitView
    },
    {
      path: '/manage',
      name: 'manage',
      component: ManageView,
      children:[
        {
          path: 'stu',
          name: 'manageStuView',
          component: ManageStuView
        },
        {
          path: 'user',
          name: 'manageUserView',
          component: ManageUserView
        }
      ]
    },
    
  ]
})
//路由守卫拦截路由
router.beforeEach((to, from, next) => {
  if(to.path === '/login'||to.path === '/submit'||to.path === '' ||to.path === '/'){
    next()
  }else {
    const userToken = localStorage.getItem('token');
    if(userToken==null||userToken.length==0){
      alert("请登录！！！！");
      return next('/login');
    }else{
      next()
    }
  }
})
export default router
