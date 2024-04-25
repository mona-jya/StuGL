<template>
  <el-container class="layout-container-demo" style="height: 100%">
    <el-container>
      <el-main>
        <el-input
            v-model="stuName"
            style="width: 240px;
            margin-left: 20px;
            margin-right: 20px;
            margin-top: 20px;
            margin-bottom: 20px;"
            placeholder="根据学生名称搜索"
            clearable
        />

        <el-button type="primary" plain style="
              margin-left: 20px;
              margin-right: 20px;
              margin-top: 20px;
              margin-bottom: 20px;" @click="getStuByName">搜索
        </el-button>
        <el-button type="primary" plain style="
              margin-left: 20px;
              margin-right: 20px;
              margin-top: 20px;
              margin-bottom: 20px;"
        @click="handleAdd"
        >新增
        </el-button>
        <el-button type="danger" style="
              margin-left: 20px;
              margin-right: 20px;
              margin-top: 20px;
              margin-bottom: 20px;"             @click="handlerMuchDel" >批量删除</el-button>
        <el-table
            ref="multipleTableRef"
            :data="stuList"
            style="width: 100%"
            @selection-change="handleSelectionChange"
        >
          <el-table-column type="selection" width="55"/>
          <el-table-column label="学生id" property="id" width="120">
          </el-table-column>
          <el-table-column property="name" label="Name" width="120"/>
          <el-table-column  label="学生头像" >
            <template #default="scope">

              <img :src="scope.row.photo+'?_='+new Date().getTime()" width="200" style="width: 80px; height: 45px">
<!--              <el-image  style="width: 70px; height: 70px" :src="'@/assets/img/15.jpg'" alt=""  ></el-image>-->
            </template>
          </el-table-column>

          <el-table-column fixed="right" align="right">
            <template #default="scope">
              <el-button  type="primary" :icon="Edit" circle @click="handleUpdate(scope.row)"/>
              <el-button type="danger" :icon="Delete" circle @click="handleDelete(scope.row)" />
            </template>
          </el-table-column>
        </el-table>
        <el-pagination background layout="prev, pager, next" :total="pageInfo.pageSize" :page-count="pageTotal" :current-page="pageInfo.pageNo" @current-change="currentPageChange" style="display:flex;justify-content: center; align-items:center;margin-top: 20px;margin-bottom: 20px;"/>
      </el-main>
      <el-dialog :title="title" v-model="editOpen" width="600px" append-to-body>
        <el-form :model="form"  ref="userRef" label-width="80px">
          <el-row>
            <el-col :span="24">
              <el-form-item label="学生id" >
                <el-input v-model="form.id"  placeholder="请输入学生id" maxlength="30" />
              </el-form-item>
            </el-col>

          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="学生姓名" >
                <el-input v-model="form.name" placeholder="请输入学生姓名" maxlength="11" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :md="12" :style="{ height: '250px' }">
              <el-form-item label="学生头像" >
                <img :src="form.photo" style="width: 200px; height: 200px" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="上传头像：" prop="fileId">
                <el-upload
                    ref="exampleUploadRef"
                :action="uploadUrl"
                :limit="1"
                list-type="picture"
                accept=".jpg,.png"
                class="avatar-uploader"
                :headers="headers"
                :before-upload="beforeUpload"
                    :on-success="uploadSuccess"
                :name="photoUpName"
                :on-exceed="handleExceed"
                :multiple="false"
                 :data="form"
                >
                <div class="uploadIcon">
                  <el-icon>
                    <Plus />
                  </el-icon>
                </div>
                <template #tip>
                  <div class="el-upload__tip text-red">只能上传jpg/png文件</div>
                </template>
                </el-upload>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button type="primary" @click="submitForm">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </template>
      </el-dialog>
      <el-dialog :title="title" v-model="addOpen" width="600px" append-to-body>
        <el-form :model="form"  ref="userRef" label-width="80px">
          <el-row>
            <el-col :span="24">
              <el-form-item label="学生id" >
                <el-input v-model="form.id"  placeholder="请输入学生id" maxlength="30" />
              </el-form-item>
            </el-col>

          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="学生姓名" >
                <el-input v-model="form.name" placeholder="请输入学生姓名" maxlength="11" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :xs="24" :md="12" :style="{ height: '250px' }">
              <el-form-item label="学生头像" >
                <img :src="form.photo" style="width: 200px; height: 200px" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24">
              <el-form-item label="上传头像(如要上传，必须要设置id!!!)：" prop="fileId" label-width="200px">
                <el-upload
                    ref="exampleUploadRef"
                    :action="uploadUrl"
                    :limit="1"
                    list-type="picture"
                    accept=".jpg,.png"
                    class="avatar-uploader"
                    :headers="headers"
                    :before-upload="beforeUpload"
                    :on-success="uploadAddSuccess"
                    :name="photoUpName"
                    :on-exceed="handleExceed"
                    :multiple="false"
                    :data="form"
                >
                  <div class="uploadIcon">
                    <el-icon>
                      <Plus />
                    </el-icon>
                  </div>
                  <template #tip>
                    <div class="el-upload__tip text-red">只能上传jpg/png文件</div>
                  </template>
                </el-upload>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <template #footer>
          <div class="dialog-footer">
            <el-button type="primary" @click="submitFormAdd">确 定</el-button>
            <el-button @click="cancel">取 消</el-button>
          </div>
        </template>
      </el-dialog>
      <el-dialog :title="title" v-model="deletOpen" width="600px" append-to-body>
        <span>删除学生id:{{form.id}}</span>
        <template #footer>
          <div class="dialog-footer">
            <el-button @click="deletOpen = false">取消</el-button>
            <el-button type="primary" @click="deleteStudent">
              确定删除
            </el-button>
          </div>
        </template>
      </el-dialog>
    </el-container>
  </el-container>
</template>

<script lang="ts" setup>
import {computed,ref,reactive,onMounted} from 'vue'
import {ElTable,ElMessage} from 'element-plus'
import {
  Delete,
  Edit,
  Download, Plus, ZoomIn

} from '@element-plus/icons-vue'
import getAxios from "@/tools/axios";

import { genFileId } from 'element-plus'
import type { UploadInstance, UploadProps, UploadRawFile } from 'element-plus'

const upload = ref<UploadInstance>()


const uploadUrl="http://localhost:8080/uploadPhoto"
const title = ref("");
const form = ref({});
const addOpen=ref(false)
const editOpen = ref(false);
const deletOpen = ref(false);
const stuName = ref('')
let pageTotal=ref(1)
let stuList:any=ref([]);
let deleteList:int=ref([]);
const pageInfo=ref({
  pageNo:1,
  pageSize:10,
})
const visible = ref(false);
const photoUpName = ref("photo");

function cancel(){
  addOpen.value = false;
  editOpen.value = false;
}

function uploadSuccess(response:string) {
  form.value.photo = response;
}
function uploadAddSuccess(response:string) {
  form.value.photo = response;
}


//处理axios拦截不了的请求，加上请求头
const headers=computed(()=>{
  let token=localStorage.getItem("token")
  return {token}
})

//判断上传之前文件的格式
const beforeUpload = (file:any) => {
  const isLt20M = file.size / 1024 / 1024 < 20;
  if (!isLt20M) {
    ElMessage({
      message: "上传图片大小不能超过 20MB!",
      type: "error",
      offset: 60
    });
  }
  var fileType = file.name.substring(file.name.lastIndexOf(".") + 1);
  const isFile = fileType === "png" || fileType === "jpg";

  if (!isFile) {
    ElMessage({
      message: "请选择正确的文件格式",
      type: "error",
      offset: 60
    });
    return false;
  }
  return isLt20M && isFile;
};
//文件超出个数限制时的钩子
const handleExceed: UploadProps['onExceed'] = (files:any) => {
  upload.value!.clearFiles()
  const file = files[0] as UploadRawFile
  file.uid = genFileId()
  upload.value!.handleStart(file)
}



interface User {
  date: string
  name: string
  address: string
}

const multipleTableRef = ref<InstanceType<typeof ElTable>>()
const multipleSelection = ref<User[]>([])
const toggleSelection = (rows?: User[]) => {
  if (rows) {
    rows.forEach((row) => {
      // TODO: improvement typing when refactor table
      // eslint-disable-next-line @typescript-eslint/ban-ts-comment
      // @ts-expect-error
      console.log(row)
      multipleTableRef.value!.toggleRowSelection(row, undefined)
    })
  } else {
    multipleTableRef.value!.clearSelection()
  }
}
const handleSelectionChange = (val: User[]) => {
  multipleSelection.value = val
  console.log(multipleSelection.value)
}


onMounted(()=>{
getStudentList();
  getStudentListForPage();
console.log(pageInfo.value)
console.log(pageTotal.value)
  // console.log(data)
})
function getStudentList(){
  // if (stuList.value.length==0){
    getAxios().axiosInstance.get("/selectStu/list").then((response)=>{
      pageTotal.value=Math.ceil(response.data.length/pageInfo.value.pageSize)
    })
        .catch((error)=>{
          console.log(error)
        })
        .finally(()=>{
          console.log("执行成功。。。。。")
        })
  // }
}

const currentPageChange = async (val) => {
  form.value={}
  // console.log(val)
  pageInfo.value.pageNo = val
  getStudentListForPage()

}

function getStuByName(){
  if(stuName.value.length!=0){
    getAxios().axiosInstance.get("/selectStuByName/"+stuName.value).then((response)=>{
      stuList.value=response.data;
    })
  }else {
    getStudentListForPage();
  }

}

function getStudentListForPage(){
  // if (stuList.value.length==0){
  getAxios().axiosInstance.post("/selectStuPage", {
    pageNo: pageInfo.value.pageNo,
    pageSize: pageInfo.value.pageSize,
  }).then((response)=>{
    // console.log(response)
    stuList.value=response.data;
    console.log(stuList)
  })
      .catch((error)=>{
        console.log(error)
      })
      .finally(()=>{
        console.log("执行成功。。。。。")
      })
  // }
}
function reset(){
  form.value={
    id: undefined,
    name: undefined,
    photo: undefined,
  }
}

function handleUpdate(row:any){
console.log(pageTotal.value)
  const userId = row.id;
  getAxios().axiosInstance.get("/selectStuById/"+userId).then((response)=>{
    form.value=response.data;
    editOpen.value=true;
   title.value = "修改学生"
  })
}
function submitForm(){
  editOpen.value=false;
  if (form.value.id!=undefined){
    getAxios().axiosInstance.put("/updateStudent",form.value).then((response)=>{
      getStudentListForPage()
    })
  }

}

function submitFormAdd(){
    addOpen.value=false;

      getAxios().axiosInstance.post("/addStu",form.value).then((response)=>{
        getStudentListForPage()
      })


}
function handleDelete(row:any){

  deletOpen.value=true;
  const userId = row.id;
  form.value.id=userId;

}
function deleteStudent(){
  getAxios().axiosInstance.delete("/deleteStudentById/"+form.value.id).then((response)=>{
    getStudentListForPage()
    deletOpen.value=false;
  })
}
function deleteMuchStudent(){
  getAxios().axiosInstance.post("/deleteStudentByList",deleteList.value).then((response)=>{
    getStudentListForPage()
  })
}

function handleAdd(){
  form.value={}
  addOpen.value=true;
  title.value = "增加学生"

}
function handlerMuchDel(){

  multipleSelection.value.forEach((row:any)=>{
    deleteList.value.push(row.id);
  })
  deleteMuchStudent()
  console.log(deleteList.value)
}
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
