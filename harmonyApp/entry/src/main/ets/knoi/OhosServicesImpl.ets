import { OhosServices } from './consumer';

export class OhosServicesImpl implements OhosServices {
  parseJson(json: string): object {
    return JSON.parse(json)
  }
}