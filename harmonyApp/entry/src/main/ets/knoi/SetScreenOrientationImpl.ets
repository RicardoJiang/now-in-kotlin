import { WindowHelper } from "../pages/WindowHelper";
import { SetScreenOrientation } from "./consumer";

export class SetScreenOrientationImpl implements SetScreenOrientation {
  setOrientation(isLandscape: boolean): void {
    WindowHelper.updateWindowOrientation(isLandscape)
  }
}