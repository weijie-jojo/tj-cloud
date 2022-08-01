<template>
  <div class="app-container">
     <uploadSmall @getfileName="getnames" :fileName="fileName" :fileNameOld="fileNameOld" :isDetail="isDetail"></uploadSmall>
  </div>
</template>

<script>
import uploadSmall from '../../components/douploads/uploadSmall.vue'

export default {
  name: 'test',
  components: {
    uploadSmall
  },
  data() {
    return {
    isDetail:'0',
    fileName:['20220726044928cc604a-7564-4ae2-bf6c-0367663fdf73加菲猫背景2.jpg'],
    fileNameOld:[{
      url:'http://storage.xuetangx.com/public_assets/xuetangx/PDF/PlayerAPI_v1.0.6.pdf',
      name:'20220726044928cc604a-7564-4ae2-bf6c-0367663fdf73加菲猫背景2.jpg'
    }]
    }

  },
  mounted() {
  },
  methods: {
    getnames(data){
      console.log("parent");
      console.log(data)
    }
  }
}
</script>
<style>

</style>