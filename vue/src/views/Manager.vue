<template>
  <div class="literalTitle">
<!--    上层-->
    <div style="height: 120px; background-image: linear-gradient(-225deg, #E3FDF5 0%, #FFE6FA 100%); display: flex; align-items: center; border-bottom: 1px solid #ddd">
      <div style="flex: 1">
        <div style="padding-left: 20px; display: flex; align-items: center">
          <img src="@/assets/imgs/logo.png" alt="" style="width: 200px ">
          <div style="font-weight: bold; font-size: 35px; font-style: italic;     margin-left: 840px; color: #39bf23 " class="literalTitle">欧sir 书籍 系统</div>
        </div>
      </div>

      <div style="width: fit-content; padding-right: 10px; display: flex; align-items: center;">
        <img style="width: 40px; height: 40px; border-radius: 50%" :src="data.user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" alt="">
        <span style="margin-left: 5px">{{ data.user.name }}</span>
      </div>

    </div>

    <div style="display: flex">
      <div style="width: 200px; border-right: 1px solid #ddd; background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%); min-height: calc(100vh - 60px) ">
        <el-menu
            router
            style="border: none"
            :default-active="router.currentRoute.value.path"
            :default-openeds="['1', '2']"
            class="a"
        >
          <el-menu-item index="/home" >
            <el-icon><House /></el-icon>
            <span>系统首页</span>
          </el-menu-item>
          <el-menu-item index="/notice" v-if="data.user.role === 'ADMIN'">
            <el-icon><Bell /></el-icon>
            <span>系统公告管理</span>
          </el-menu-item>
<!--  管理员可以去  预览-->
          <el-menu-item index="/buy"  v-if="data.user.role === 'USER'">
            <el-icon><Goods /></el-icon>
            <span >书籍购买</span>
          </el-menu-item>
          <el-menu-item index="/buy"  v-if="data.user.role === 'ADMIN'">
            <el-icon><Goods /></el-icon>
            <span >预览购买界面</span>
          </el-menu-item>

          <el-menu-item index="/orders">
            <el-icon><Tickets /></el-icon>
            <span>订单管理</span>
          </el-menu-item>

          <el-sub-menu index="1" v-if="data.user.role === 'ADMIN'">
            <template #title>
              <el-icon><Notebook /></el-icon>
              <span>书籍管理</span>
            </template>
                <el-menu-item index="/category">
                  <el-icon><Menu /></el-icon>
                  <span>书籍类型分类管理</span>
                </el-menu-item>
                <el-menu-item index="/goods">
                  <el-icon><Goods /></el-icon>
                  <span>书籍管理</span>
                </el-menu-item>
                <el-menu-item index="/goodsStock">
                  <el-icon><SoldOut /></el-icon>
                  <span>书籍进货管理</span>
                </el-menu-item>
          </el-sub-menu>


          <el-sub-menu index="2" v-if="data.user.role === 'ADMIN'">
            <template #title>
              <el-icon><Memo /></el-icon>
              <span>用户管理</span>
            </template>
                <el-menu-item index="/admin">
                  <el-icon><User /></el-icon>
                  <span>管理员信息</span>
                </el-menu-item>
                <el-menu-item index="/user">
                  <el-icon><User /></el-icon>
                  <span>普通用户信息</span>
                </el-menu-item>
          </el-sub-menu>

          <el-menu-item index="/person">
            <el-icon><User /></el-icon>
            <span>个人资料</span>
          </el-menu-item>

          <el-menu-item index="/password">
            <el-icon><Lock /></el-icon>
            <span>修改密码</span>
          </el-menu-item>

          <el-menu-item index="login" @click="logout">
            <el-icon><SwitchButton /></el-icon>
            <span>退出系统</span>
          </el-menu-item>

          <el-menu-item index="/new" >
            <el-icon><SwitchButton /></el-icon>
            <span>拓展区域</span>
          </el-menu-item>

        </el-menu>
      </div>

      <div style="flex: 1; width: 0; background-image: linear-gradient(120deg, #a1c4fd 0%, #c2e9fb 100%); padding: 10px">
        <router-view @updateUser="updateUser" />
      </div>

    </div>

  </div>
</template>

<script setup>
import { reactive } from "vue";
import router from "@/router";
import {ElMessage} from "element-plus";

const data = reactive({
  user: JSON.parse(localStorage.getItem('system-user') || '{}')
})

if (!data.user?.id) {
  ElMessage.error('请登录！')
  router.push('/login')
}

const updateUser = () => {
  data.user = JSON.parse(localStorage.getItem('system-user') || '{}')
}

const logout = () => {
  ElMessage.success('退出成功')
  localStorage.removeItem('system-user')
  router.push('/login')
}
</script>

<style scoped>
.a {
  background-image: linear-gradient(-225deg, #5D9FFF 0%, #B8DCFF 48%, #6BBBFF 100%);
}
.el-menu-item:hover {
  color: #1967e3;
}
.literalTitle {
  font-family: 'Comic Sans MS', cursive;
  font-size: 30px;
}
:a(th)  {
  color: #333;
}
</style>