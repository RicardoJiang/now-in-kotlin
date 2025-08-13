/*
 * Tencent is pleased to support the open source community by making ovCompose available.
 * Copyright (C) 2025 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { promptAction } from '@kit.ArkUI';


interface ButtonArgs {
  text: string
  backgroundColor: string
}

@Builder
export function buttonBuilder(args: ButtonArgs) {
  button()
}

@Component
export struct button {
  @Consume compose_args: ButtonArgs

  build() {
    Column() {
      Button(this.compose_args.text).backgroundColor(this.compose_args.backgroundColor).width('100%').onClick(e => {
        console.log(`Button Clicked: ${this.compose_args.text}`)
      }).height('70%')

      Text(this.compose_args.text).backgroundColor(this.compose_args.backgroundColor).width('100%').onClick(e => {
        console.log(`Text Clicked: ${this.compose_args.text}`)
      }).height('20%')
      Stack().height('10%')
    }
  }
}

interface TextArgs {
  id: string
  text: string
  backgroundColor: string
}

@Builder
export function textBuilder(args: TextArgs) {
  text()
}

@Component
export struct text {
  @Consume compose_args: TextArgs

  build() {
    Text(this.compose_args.id + " " + this.compose_args.text)
      .backgroundColor(this.compose_args.backgroundColor)
      .width('100%')
      .height('100%')
      .borderRadius('5vp')
      .onClick(e => {
        console.log(`Text Clicked: ${JSON.stringify(this.compose_args)}`)
      })
  }
}

@Builder
export function labelBuilder(args: ButtonArgs) {
  label()
}

@Component
export struct label {
  @Consume compose_args: ButtonArgs

  build() {
    Column() {

      Button(`+ ${this.compose_args.text}`, { type: ButtonType.Normal })
        .backgroundColor(this.compose_args.backgroundColor)
        .width('100%')
        .height('50%')
        .borderRadius(2)
        .onClick(e => {
          promptAction.showToast({
            message: this.compose_args.text
          })
        })
      Text(this.compose_args.text).backgroundColor(Color.Orange).width('100%').height('50%')
    }
  }
}

@Builder
export function layerBuilder(args: ButtonArgs) {
  layer()
}

@Component
export struct layer {
  @Consume compose_args: ButtonArgs

  build() {
    Stack({ alignContent: Alignment.BottomEnd }) {
      Text(this.compose_args.text)
    }
    .width('100%')
    .height('100%')
    .backgroundColor(this.compose_args.backgroundColor)
    .onClick(() => {
      promptAction.showToast({ message: `${this.compose_args.text} Clicked` })
    })
    .onTouch(e => {
      console.log(`layer ${this.compose_args.text} onTouch`)
    })
  }
}

@Builder
export function buttonWrapContentBuilder(args: ButtonArgs) {
  buttonWrapContent()
}

@Component
export struct buttonWrapContent {
  @Consume compose_args: ButtonArgs

  build() {
    Column() {

      Button(this.compose_args.text).backgroundColor(this.compose_args.backgroundColor).width('100%').onClick(e => {
        console.log(`Button Clicked: ${this.compose_args.text}`)
      })
      Text(this.compose_args.text).backgroundColor(this.compose_args.backgroundColor).width('100%').onClick(e => {
        console.log(`Text Clicked: ${this.compose_args.text}`)
      })
    }
  }
}


interface InputArgs {
  text: string
}

@Builder
export function textInputBuilder(args: InputArgs) {
  textInput()
}


@Component
export struct textInput {
  @Consume compose_args: InputArgs

  build() {
    Column() {
      TextInput({ text: $$this.compose_args.text }).onChange(value => {
        console.log(`/// onChange: ${value}`)
      })

      Text("ArkUI组件更新:").fontColor(Color.Gray).margin({ top: 30 })

      Text(this.compose_args.text)
        .width('100%')
        .padding(10)
        .border({ width: 1, color: Color.Gray })
    }.alignItems(HorizontalAlign.Start)
  }
}

@Builder
export function verticalListBuilder(args?: object) {
  verticalList()
}

@Component
export struct verticalList {
  build() {
    Row() {
      List() {
        ForEach([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], (item: number, index) => {
          ListItem() {
            Button(`Ark Button ${index}`, { type: ButtonType.Normal }).borderRadius(2).height('100vp').padding(10)
          }.padding(10)
        })
      }.listDirection(Axis.Vertical).height('100%').width('40%')

      Stack().width('20%').height('100%').borderWidth(1)

      Scroll() {
        Column() {
          ForEach([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], (item: number, index) => {
            ListItem() {
              Button(`Ark Button ${index}`, { type: ButtonType.Normal }).borderRadius(2).height('100vp').padding(10)
            }.padding(10)
          })
        }
      }.scrollable(ScrollDirection.Vertical).height('100%').width('40%')
    }
  }
}

@Builder
export function horizontalListBuilder(args?: object) {
  horizontalList()
}

@Component
export struct horizontalList {
  build() {
    Column() {
      List() {
        ForEach([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], (item: number, index) => {
          ListItem() {
            Button(`Ark Button ${index}`, { type: ButtonType.Normal }).borderRadius(2).height('100vp')
          }.padding(10)
        })
      }.listDirection(Axis.Horizontal).height('40%').width('100%')

      Stack().width('100%').height('20%').borderWidth(1)

      Scroll() {
        Row() {
          ForEach([1, 2, 3, 4, 5, 6, 7, 8, 9, 10], (item: number, index) => {
            ListItem() {
              Button(`Ark Button ${index}`, { type: ButtonType.Normal }).borderRadius(2).height('100vp')
            }.padding(10)
          })
        }
      }.scrollable(ScrollDirection.Horizontal).height('40%').width('100%')
    }
  }
}
