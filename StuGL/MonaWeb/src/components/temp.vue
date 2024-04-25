<template>
  <BreadCrumb ref="breadCrumb" :item="item"></BreadCrumb>
  <div class="table-wrapped">
    <div class="table-top">
      <div class="table-header">
        <div class="search-wrapped" style="display: flex">
          <el-input v-model="input1" class="w-50 m-2" placeholder="输入账号搜索" :prefix-icon="Search"
                    @change="searchAdmin" />
          <!-- <el-button type="primary" @click="getAdmin" style="margin-left: 10px;" circle :icon="Refresh"
              title="重置列表"></el-button> -->
        </div>
        <div class="button-wrapped">
          <el-button type="primary" @click="create">添加产品管理员</el-button>
        </div>
      </div>
      <div class="table-content">
        <el-table :data="tableData" border style="width: 100%">
          <el-table-column type="index" width="50" />
          <el-table-column prop="account" label="账号" />
          <el-table-column prop="name" label="姓名" />
          <el-table-column prop="sex" label="性别" />
          <el-table-column prop="department" label="部门" />
          <el-table-column prop="email" label="邮箱" />
          <el-table-column prop="update_time" label="更新时间" />

          <el-table-column label="操作" width="150">
            <template #default="scope">
              <el-button type="success" size="small"
                         @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button type="danger" size="small"
                         @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>

        </el-table>
      </div>
    </div>
    <div class="table-footer">
      <el-pagination :page-size="2" :current-page="paginationData.currentPage" :pager-count="5" :total="adminTotal"
                     :page-count="paginationData.pageCount" @current-change="currentPageChange" layout="prev, pager, next" />
    </div>
  </div>
  <CreateAdmin></CreateAdmin>
  <EditAdmin></EditAdmin>
  <DeleteAdmin></DeleteAdmin>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'
import { Refresh, Search } from '@element-plus/icons-vue'
import BreadCrumb from '@/components/BreadCrumb.vue';
import CreateAdmin from '../components/CreateAdmin.vue'
import EditAdmin from '../components/EditAdmin.vue'
import DeleteAdmin from '../components/DeleteAdmin.vue';
import { getAdminAPI, searchUserAPI, getAdminListLengthAPI, returnListDataAPI } from "@/apis/userinfo";
import mitt from '@/utils/mitt'
import { ElMessage } from 'element-plus';
const emitter = mitt

const item = ref({
  first: '用户管理',
  second: '产品管理员'
})
const input1 = ref('')
const tableData = ref([])

const create = () => {
  emitter.emit('openCreateDialog', '添加产品管理员')
}

const handleEdit = (index, row) => {
  emitter.emit('openEditDialog', { index, row, title: '编辑产品管理员' })
  // console.log('-----------', index, row)
}
const handleDelete = (index, row) => {
  emitter.emit('openDeleteDialog', { row })
}

const getAdmin = async () => {
  const res = await getAdminAPI({ identity: '产品管理员' })
  if (res.status && res.status == 1) return ElMessage.error('获取数据出错')
  tableData.value = res
  // console.log(res)
}

emitter.on('refreshAdminList', async () => {
  // getAdmin()
  getAdminListLength()
  tableData.value = await returnListDataAPI({ identity: '产品管理员', page: paginationData.value.currentPage - 1 })

})
const searchAdmin = async () => {
  const res = await searchUserAPI({ account: input1.value })
  // console.log(res)
  tableData.value = res
}

// 分页
const paginationData = ref({
  // 总页数
  pageCount: 1,
  // 当前页
  currentPage: 1,
})

const adminTotal = ref(0)
const getAdminListLength = async () => {
  const res = await getAdminListLengthAPI({ identity: '产品管理员' })
  adminTotal.value = res.length
  // 每页显示10条数据，所以除以10
  paginationData.value.pageCount = Math.ceil(res.length / 10)
}

// 默认获取第一页的数据
const getFirstPageList = async () => {
  tableData.value = await returnListDataAPI({ identity: '产品管理员', page: 0 })
}
const currentPageChange = async (val) => {
  // console.log(val)
  paginationData.value.currentPage = val
  tableData.value = await returnListDataAPI({ identity: '产品管理员', page: val - 1 })
}
onMounted(() => {
  // getAdmin()
  getAdminListLength()
  getFirstPageList()
})
onBeforeUnmount(() => {
  emitter.all.clear()
})
</script>

<style lang="scss" scoped></style>
