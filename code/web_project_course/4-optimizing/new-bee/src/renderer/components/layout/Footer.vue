<template>
    <footer class="footer footer-light-medium " style="padding-bottom: 20px;padding-top: 20px;">
    <div class="container">
        <div class="columns">
            <!-- Column -->
            <div class="column is-4">
                <div class="mb-20">
                    <img class="small-footer-logo" src="https://img.actd.tw/images/2018/11/17/bee.png" alt="">
                    <div class="footer-description pt-10">
                        new bee 是一个为开发者提供的专注于技术分享的开源社区,所有源码均可在 github 上找到,希望对广大开发者有所帮助。
                    </div>
                </div>
                <div>
                    <span class="moto">喜欢项目可以点赞支持  <a href="https://github.com/pkwenda/new-bee" target="_blank">
                            <span class="icon"><i class="fa fa-github"></i></span>
                        </a>.</span>
                    <div class="social-links mt-20">
                         
                        
                    </div>
                </div>
            </div>
            <!-- Column -->
            <div class="column is-6 is-offset-2">
                <div class="columns">
                    <!-- Column -->
                    <div class="column">
                        <ul class="footer-column">
                            <li class="column-header">
                                Links
                            </li>
                            <li class="column-item"><a href="https://github.com/pkwenda/new-bee">Home</a></li>
                            <li class="column-item"><a href="https://cssninja.io/themes">Blog</a></li>
                            <li class="column-item"><a href="https://github.com/pkwenda/new-bee/wiki">Wiki</a></li>
                        </ul>
                    </div>
                    <!-- Column -->
                    <div class="column">
                        <ul class="footer-column">
                            <li class="column-header">
                                Ressources
                            </li>
                            <li class="column-item"><a href="https://cssninja.io/help">Help center</a></li>
                            <li class="column-item"><a href="https://cssninja.io/blog">Blog</a></li>
                            <li class="column-item"><a href="https://cssninja.io/help/rules">Rules</a></li>
                        </ul>
                    </div>
                    <!-- Column -->
                    <div class="column">
                        <ul class="footer-column">
                            <li class="column-header">
                                Terms
                            </li>
                            <li class="column-item"><a href="https://cssninja.io/help/terms/licenses/personal">Personal</a></li>
                            <li class="column-item"><a href="https://cssninja.io/help/terms/licenses/developer">Developer</a></li>
                            <li class="column-item"><a href="https://cssninja.io/help/terms/service">Terms of Service</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</footer>
</template>


<script>
export default {
  name: "Footer",
  data() {
    return {};
  },
  created() {},
  destroyed() {},
  mounted() {},
  methods: {}
};
</script>

<style scoped>
.bd-article-overlay {
  bottom: 0;
  left: 0;
  position: absolute;
  right: 0;
  top: 0;
  background-color: #0a0a0a;
  opacity: 0;
  -webkit-transition-duration: 86ms;
  transition-duration: 86ms;
  -webkit-transition-property: opacity;
  transition-property: opacity;
  -webkit-transition-timing-function: ease-out;
  transition-timing-function: ease-out;
}
.bd-article-image {
  background-color: #00d1b2;
  display: block;
  height: 240px;
  margin-left: auto;
  margin-right: auto;
  position: relative;
  text-align: center;
}
</style>
