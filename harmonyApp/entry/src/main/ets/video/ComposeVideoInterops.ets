import { display } from "@kit.ArkUI";
import { media } from "@kit.MediaKit";
import { common } from "@kit.AbilityKit";
import { emitter } from "@kit.BasicServicesKit";
import { AVPlayerManager } from "./AVPlayerManager";

interface VideoArgs {
  url: string
}

@Builder
export function harmonyVideoBuilder(args: VideoArgs) {
  harmonyVideo()
}

@Component
export struct harmonyVideo {
  @Consume compose_args: VideoArgs
  private xComponentController: XComponentController = new XComponentController();
  private avPlayerManager: AVPlayerManager = AVPlayerManager.getInstance();
  private surfaceId: string = '';
  tag: string = 'AVPlayManager';
  @State windowWidth: number = 300;
  @State windowHeight: number = 200;
  PROPORTION = 0.99; // 占屏幕比例
  SURFACE_W = 0.9; // 表面宽比例
  SURFACE_H = 1.78; // 表面高比例
  private intervalID: number = -1;
  private context: common.UIAbilityContext | undefined = undefined;

  aboutToAppear(): void {
    this.windowWidth = display.getDefaultDisplaySync().width;
    this.windowHeight = display.getDefaultDisplaySync().height;
    this.context = getContext(this) as common.UIAbilityContext;
  }

  aboutToDisappear(): void {
    this.avPlayerManager.releaseAVPlayer();
  }

  build() {
    XComponent({
      // 装载视频容器
      id: 'xComponent',
      type: XComponentType.SURFACE,
      controller: this.xComponentController
    })
      .id('VideoView')
      .onLoad(() => {
        this.surfaceId = this.xComponentController.getXComponentSurfaceId();
        this.avSetupVideo();
      })
      .width('100%').height('100%').renderFit(RenderFit.RESIZE_CONTAIN)
  }

  async avSetupVideo() {
    // 通过UIAbilityContext的resourceManager成员的getRawFd接口获取媒体资源播放地址。
    // 返回类型为{fd,offset,length},fd为HAP包fd地址，offset为媒体资源偏移量，length为播放长度。
    if (this.context == undefined) return;

    // 创建avPlayer实例对象
    await this.avPlayerManager.createAVPlayer();

    // 创建状态机变化回调函数
    await this.avPlayerManager.setAVPlayerCallback((avPlayer: media.AVPlayer) => {

    }, this.surfaceId);

    // 为url赋值触发initialized状态机上报
    this.avPlayerManager.setUrl(this.compose_args.url);
  }

}