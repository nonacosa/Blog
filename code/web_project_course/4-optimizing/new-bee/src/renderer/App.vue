<template>
  <div id="app">
     <!-- <Header></Header> -->
    <!-- <img src="./assets/logo.png"> -->
    <router-view/>
  </div>
</template>

<script>
import Header from "@/components/layout/Header";
export default {
  name: "App",
  components: { Header }
};
</script>

<style>
</style>
