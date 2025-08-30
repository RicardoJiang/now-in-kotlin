import { OhosServices } from './consumer';

export class OhosServicesImpl implements OhosServices {
  parseJson(json: string): object {
    console.log('here parse json')
    return JSON.parse(json)
  }
}