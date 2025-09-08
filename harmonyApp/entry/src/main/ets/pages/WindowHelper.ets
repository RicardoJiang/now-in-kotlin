import { window } from '@kit.ArkUI';

export class WindowHelper {
  static windowClass ?: window.Window = undefined

  static updateStatusBarColor() {
    WindowHelper.windowClass?.setWindowSystemBarProperties({
      statusBarColor: '#FF0B1220',
      statusBarContentColor: '#ffffff',
      navigationBarColor: '#FF0B1220',
      navigationBarContentColor:'#ffffff'
    });
  }
}