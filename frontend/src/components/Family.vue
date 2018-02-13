<template>
  <div class="family">
    <ul>
      <li>序号</li>
      <li>名称</li>
    </ul>
    <ul v-for="family in families" :key="family.id">
      <li>{{ family.id }}</li>
      <li>{{ family.name }}</li>
    </ul>
    <button v-on:click="showData">显示数据</button>
  </div>
</template>

<script>
var axios = require('axios')

export default {
  name: 'family',
  data () {
    return {
      families: []
    }
  },
  methods: {
    showData: function () {
      var that = this
      axios.get('/api/families')
        .then(function (response) {
          that.families = response.data.data
        })
        .catch(function (error) {
          // Wu oh! Something went wrong
          console.log(error.message)
        })
    }
  }
}
</script>
