<template>
    <div>
     <Header></Header>
     <Content></Content>
     <Footer></Footer> 
    </div>
</template>

<script>
import Header from "@/components/layout/Header";
import Footer from "@/components/layout/Footer";
import Content from "@/components/layout/Content";
import _ from "lodash";
export default {
  name: "NewBeeIndex",
  components: { Header, Footer, Content },
  data() {
    return {};
  },
  destroyed() {},
  mounted() {},
  methods: {},
  watch: {}
};
</script>
 