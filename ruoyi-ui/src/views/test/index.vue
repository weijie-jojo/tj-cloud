<template>
  <div>
      <pdf   ref="pdf" src="http://192.168.2.17/漏洞和隐患发现工作备案表.pdf" ></pdf>
  </div>  
</template>

<script>
import pdf from "vue-pdf";
export default {
    components:{
        pdf
    }
 }
</script>

<style>

</style>