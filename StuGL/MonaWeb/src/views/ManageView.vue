<template>
  <el-container class="layout-container-demo" style="height: 100%">

    <el-aside width="200px">
      <h3 id="text"
          style="text-align: center;padding-left: 5px;padding-top: 5px;padding-bottom: 5px;padding-right: 5px;">
        学生信息管理系统
      </h3>
      <el-scrollbar>
        <el-menu :default-openeds="['1', '3']">
          <el-sub-menu index="1">
            <template #title>
              <el-icon>
                <message/>
              </el-icon>
              学生管理
            </template>
            <el-menu-item-group>

              <el-menu-item index="1-1">
                <RouterLink :to="{path:'/manage/stu'}" > 管理学生</RouterLink>

              </el-menu-item>

            </el-menu-item-group>

          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              <el-icon>
                <icon-menu/>
              </el-icon>
              登录账号管理
            </template>
            <el-menu-item-group>

              <el-menu-item index="2-1">
                <RouterLink :to="{path:'/manage/user'}" > 账号管理</RouterLink>
                </el-menu-item>

            </el-menu-item-group>

          </el-sub-menu>

        </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <div class="toolbar">
          <el-dropdown>
            <el-icon style="margin-right: 8px; margin-top: 1px">
              <setting/>
            </el-icon>
            <template #dropdown>
              <el-dropdown-menu>
                <el-dropdown-item @click="unLogin">登出
                </el-dropdown-item>
              </el-dropdown-menu>
            </template>
          </el-dropdown>
          <span>{{ userName }}</span>
        </div>
      </el-header>


        <h3>欢迎来到学生信息管理系统</h3>
        <router-view/>

    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import {ref,onMounted,onUnmounted} from 'vue'
import {ElTable} from 'element-plus'
import {Menu as IconMenu, Message, Setting} from '@element-plus/icons-vue'
import getAxios from "@/tools/axios";
import router from "@/router";

let userName=ref("")

const input = ref('')
onMounted(()=>{

  userName.value=(localStorage.getItem("userName"));
})
onUnmounted(()=>{
  localStorage.removeItem("userName")
})
const item = {
  date: '2016-05-02',
  name: 'Tom',
  address: 'No. 189, Grove St, Los Angeles',
}

interface User {
  date: string
  name: string
  address: string
}

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<User[]>([])


function unLogin(){
  localStorage.removeItem('token')
  router.push("/login")
}

const toggleSelection = (rows?: User[]) => {
  if (rows) {
    rows.forEach((row) => {
      // TODO: improvement typing when refactor table
      // eslint-disable-next-line @typescript-eslint/ban-ts-comment
      // @ts-expect-error
      multipleTableRef.value!.toggleRowSelection(row, undefined)
    })
  } else {
    multipleTableRef.value!.clearSelection()
  }
}
const handleSelectionChange = (val: User[]) => {
  multipleSelection.value = val
}

const tableData: User[] = [
  {
    date: '2016-05-03',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-02',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-04',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-01',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-08',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-06',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
  {
    date: '2016-05-07',
    name: 'Tom',
    address: 'No. 189, Grove St, Los Angeles',
  },
]

</script>

<style scoped>
.layout-container-demo .el-header {
  position: relative;
  background-color: var(--el-color-primary-light-7);
  color: var(--el-text-color-primary);
}

.layout-container-demo .el-aside {
  color: var(--el-text-color-primary);
  background: var(--el-color-primary-light-8);
}

.layout-container-demo .el-menu {
  border-right: none;
}

.layout-container-demo .el-main {
  padding: 0;
}

.layout-container-demo .toolbar {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  right: 20px;
}

text {
  text-align: center;

}
</style>
