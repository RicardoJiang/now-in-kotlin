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

import UIKit

@objc class ImagesScrollView: UIScrollView {
    
    // MARK: - Initialization
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        commonInit()
    }
    
    required init?(coder: NSCoder) {
        super.init(coder: coder)
        commonInit()
    }
    
    // MARK: - Setup
    
    private func commonInit() {
        configureAppearance()
        addImageViews()
    }
    
    private func configureAppearance() {
        backgroundColor = .systemGray5
        showsVerticalScrollIndicator = true
        showsHorizontalScrollIndicator = true
    }
    
    private func addImageViews() {
        let imageViews = (1...6).map { index -> UIImageView in
            let imageName = "img-type-\(index)"
            let image = UIImage(named: imageName)
            
            let imageView = UIImageView(frame: CGRect(
                x: index * 80 + 20,
                y: index * 80 + 20,
                width: 80,
                height: 80
            ))
            imageView.image = image
            imageView.contentMode = .scaleAspectFit
            imageView.clipsToBounds = true
            return imageView
        }
        
        imageViews.forEach { addSubview($0) }
        
        
        let maxX = imageViews.map { $0.frame.maxX }.max() ?? 0
        let maxY = imageViews.map { $0.frame.maxY }.max() ?? 0
        contentSize = CGSize(
            width: maxX + 20,
            height: maxY + 20
        )
    }
}
