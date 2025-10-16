import { window } from '@kit.ArkUI';
import { BusinessError } from '@kit.BasicServicesKit';

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

  static updateWindowOrientation(isLandscape: boolean) {
    const orientation = isLandscape ? window.Orientation.LANDSCAPE : window.Orientation.PORTRAIT
    WindowHelper.windowClass?.setPreferredOrientation(orientation, (err: BusinessError) => {
      const errCode: number = err.code;
      if (errCode) {
        console.error(`Failed to set window orientation. Cause code: ${err.code}, message: ${err.message}`);
        return;
      }
      console.info('Succeeded in setting window orientation.');
    });
  }
}