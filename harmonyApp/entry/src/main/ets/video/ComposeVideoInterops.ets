import { common } from '@kit.AbilityKit';

interface VideoArgs {
  url: string
  surfaceId: string
}

@Builder
export function harmonyVideoBuilder(args: VideoArgs) {
  harmonyVideo()
}

@Component
export struct harmonyVideo {
  @Consume compose_args: VideoArgs
  private xComponentController: XComponentController = new XComponentController();
  private surfaceId: string = '';
  tag: string = 'AVPlayManager';
  PROPORTION = 0.99; // 占屏幕比例
  SURFACE_W = 0.9; // 表面宽比例
  SURFACE_H = 1.78; // 表面高比例
  private context: common.UIAbilityContext | undefined = undefined;

  aboutToAppear(): void {
    this.context = getContext(this) as common.UIAbilityContext;
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
        this.compose_args.surfaceId = this.surfaceId
      })
      .width('100%').height('100%').renderFit(RenderFit.RESIZE_CONTAIN)
  }

}