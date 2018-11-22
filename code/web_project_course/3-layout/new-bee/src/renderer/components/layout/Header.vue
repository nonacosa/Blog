<template>

    <div id="bee-header"   element-loading-text="正在努力请求github..." element-loading-background="rgba(0, 0, 0, 0.8)">
        <!-- 遮罩 -->
        <div  :class="  loading ? `modal is-active` : `modal` " style="background-color: #ffffff36">
                <img src="https://img.actd.tw/images/2018/11/17/ezgif-4-05f4bba41fef.gif" style="width: 300px" alt="">
        </div>

        <div class="is-underline ">
    <div class="container">
      <nav class="navbar ">
        <div class="navbar-brand">
          <a class="navbar-item"   >
            <img src="https://img.actd.tw/images/2018/11/17/bee.png" alt="Bulma: a modern CSS framework based on Flexbox" width="92" height="28">
          </a>
          <div class="login-before is-hidden-mobile" style="padding-top: 5px;">
            <a class="navbar-item is-hidden-desktop" href="https://github.com/pkwenda/my-bbs" target="_blank">
              <span class="icon" style="color: #333;">
                <i class="fa fa-lg fa-github is-size-2"></i>
              </span>
            </a>
          </div>
          <div class="navbar-item is-hidden-desktop ">
             <div class="field has-addons"  ><div class="control" ><input   type="input" class="input" name="email" placeholder="搜索一下" required="required" style="height: 36.4px;width:130px"><input   type="hidden" name="redirect" id="name" value="/fr/#thanks"></div><div class="control"  ><input  type="submit" class="button is-warning" value="GO"></div></div>
          </div>
           
          <div class="navbar-burger burger" data-target="navMenuDocumentation" >
            <span></span>
            <span></span>
            <span></span>
          </div>
        </div>

        <div id="navMenuDocumentation" class="navbar-menu">
          <div class="navbar-start">
            <div class="navbar-item has-dropdown is-hoverable">
              <a class="navbar-link  is-active">
                发现
              </a>
              <div class="navbar-dropdown ">
                <a class="navbar-item " type="收藏集">
                  收藏集
                </a>
                <a class="navbar-item" type="徽章">
                  徽章
                </a>
                <a class="navbar-item " type="排名">
                  排名
                </a>
                <a class="navbar-item "  type="职场生活">
                  职场生活
                </a>
              </div>
            </div>
            <a class="navbar-item " href="https://bulma.io/expo/">
              <!--<span class="bd-emoji">⭐️</span>-->
              专栏
            </a>
            <a class="navbar-item " href="https://bulma.io/expo/">
              <!--<span class="bd-emoji">⭐️</span>-->
              聊天
              <!-- 很多人不知道干什么。。。 -->
            </a>
            <a class="navbar-item " href="https://bulma.io/expo/">
              <!--<span class="bd-emoji">⭐️</span>-->
              面经
            </a>
            <router-link class="navbar-item " to="/book">
              <!--<span class="bd-emoji">❤️</span>-->
              书籍
            </router-link>
          </div>

          <div class="navbar-end">
            <div class="login-before" style="padding-top: 5px;">
              <!-- pc -->
              <a class="navbar-item is-hidden-desktop-only" href="https://github.com/pkwenda/my-bbs" target="_blank">
                <span class="icon" style="color: #333;">
                  <i class="fa fa-lg fa-github is-size-2"></i>
                </span>
              </a>
            </div>

            <div class="navbar-item is-hidden-mobile ">
               <div class="field has-addons"  ><div class="control" ><input   type="input" class="input" name="email" placeholder="搜索一下" required="required" style="height: 36.4px;"><input   type="hidden" name="redirect" id="name" value="/fr/#thanks"></div><div class="control"  ><input  type="submit" class="button is-warning" value="GO"></div></div>
            </div>

            <div class="navbar-item is-hidden-mobile ">
              <!--<span class="icon is-medium">-->
              <i class="iconfont icon-tixing"></i>
              <!--</span>-->
            </div>

               
                              <a class="is-hidden-mobile"  target="_blank">
                              
                                <img :src="userInfo.avatarPath" class=" header-avatar img-circle "
                                  style="margin-top: 10px">
                              </a>
 
           
            <div class="login-before">
              <div class="navbar-item">
                <div class="field is-grouped">
                 
                  <p class="control">
                    <a class="button is-warning"  v-show="!isLogin"  >
                      <strong>登录</strong>
                    </a>

                  </p>

                </div>
              </div>
            </div>
          </div>
        </div>
      </nav>
    </div>
  </div>
    </div>
</template>


<script>
import _ from "lodash";
export default {
  name: "BeeHeader",

  data() {
    return {
      popupShow: false,
      isLogin: false,
      user: {},
      loading: false,
      userInfo: {}
    };
  },
  created() {},
  destroyed() {},
  mounted() {},
  methods: {}
};
</script>
