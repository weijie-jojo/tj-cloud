<template>
  <div>
    <pdf
      v-for="i in numPages"
      :key="i"
      :src="src"
      :page="i"
    />
  </div>
</template>

<script>

import pdf from 'vue-pdf'

var loadingTask = pdf.createLoadingTask('http://storage.xuetangx.com/public_assets/xuetangx/PDF/PlayerAPI_v1.0.6.pdf')

export default {
  name: 'HomedDd',
  components: {
    pdf
  },
  data() {
    return {
      src: loadingTask,
      numPages: undefined
    }
  },
  mounted() {
    this.src.promise.then(pdf => {
      this.numPages = pdf.numPages
    })
  }
}
</script>