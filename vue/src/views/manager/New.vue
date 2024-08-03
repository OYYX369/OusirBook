<template>
  <!-- 提交表单 -->
  <div>
    <el-form-item label="头像">
      <el-upload
          class="avatar-uploader"
          action="http://book-back.ousir.icu/files/upload"
          :show-file-list="false"
          :on-success="handleAvatarSuccess"
          :before-upload="beforeAvatarUpload"
          :data="imageFormData"
          name="files"
          accept="image/*"
      >
        <img v-if="imageUrl" :src="imageUrl" class="avatar" />
        <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
      </el-upload>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">提交</el-button>
    </el-form-item>

  </div>
</template>

<script  setup>
import { ref, onMounted } from "vue";
import { ElMessage } from "element-plus";
import { Plus } from "@element-plus/icons-vue";
import axios from "axios";


//提交表单数据
const formInline = ref({});
//照片
const imageUrl = ref("");
//上传图片表单
const imageFormData = ref({
  type: "picture",
});
//记录图片ID
const imageId = ref("");

const handleAvatarSuccess = (response, file) => {
  console.log("response=", response);
  console.log("file=", file);
  console.log("上传成功后的id=", response.data[0]);
  imageId.value = response.data[0];

  //回显图片
  getImage(imageId.value);
};

//调用图片
const getImage = async (id) => {
  //根据ID调用接口获取图片

  try {
    const imageInfo = await axios
        .get("http://book-back.ousir.icu/files/upload" + id, {
          responseType: "blob",
          headers: {
            Accept:
                "image/avif,image/webp,image/apng,image/svg+xml,image/*,*/*;q=0.8",
          },
        })
        .then((response) => {
          const blob = new Blob([response.data], { type: "image/*" });
          imageUrl.value = URL.createObjectURL(blob);
        });
  } catch (error) {
    console.error("获取数据失败", error);
  }
};

//校验文件
const beforeAvatarUpload = (rawFile) => {
  if (!rawFile.type.startsWith("image/")) {
    ElMessage.error("请上传图片！");
    return false;
  } else if (rawFile.size / 1024 / 1024 > 2) {
    ElMessage.error("图片大小不能超过2MB！");
    return false;
  }
  return true;
};

const onSubmit = () => {
  console.log("onSubmit");
};

</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}
.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #000;
  width: 100px;
  height: 100px;
  text-align: center;
}
.avatar-uploader .avatar {
  width: 100px;
  height: 100px;
}
</style>
