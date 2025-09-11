import UIKit
import SwiftUI
import ComposeApp

class WrapperViewController: UIViewController {
    private let composeVC = MainViewControllerKt.MainViewController()

    override func viewDidLoad() {
        super.viewDidLoad()
        
        // 设置背景色为 #0B1220
        view.backgroundColor = UIColor(red: 11/255.0, green: 18/255.0, blue: 32/255.0, alpha: 1.0)

        
        addChild(composeVC)
        view.addSubview(composeVC.view)
        
        composeVC.view.translatesAutoresizingMaskIntoConstraints = false
        
        // --- 主要改动在这里 ---
        // 将约束从 view 的边缘改为 view.safeAreaLayoutGuide 的边缘
        let guide = view.safeAreaLayoutGuide
        NSLayoutConstraint.activate([
            composeVC.view.topAnchor.constraint(equalTo: guide.topAnchor),
            composeVC.view.bottomAnchor.constraint(equalTo: guide.bottomAnchor),
            composeVC.view.leadingAnchor.constraint(equalTo: guide.leadingAnchor),
            composeVC.view.trailingAnchor.constraint(equalTo: guide.trailingAnchor),
        ])
        
        composeVC.didMove(toParent: self)
    }

    override var preferredStatusBarStyle: UIStatusBarStyle {
        return .lightContent
    }
}


struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        return WrapperViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
            // 仍然需要忽略安全区域，以便 WrapperViewController 的背景能延伸到屏幕边缘
            .ignoresSafeArea()

        // TabView {
        //     // UIKit 渲染
        //     ComposeView()
        //         .ignoresSafeArea(.keyboard)
        //         .tabItem {
        //             Label("Compose", systemImage: "rectangle.3.group.fill")
        //         }
        //
        //     // Skia 渲染
        //     ComposeSkiaView()
        //         .ignoresSafeArea(.keyboard)
        //         .tabItem {
        //             Label("Skia", systemImage: "s.circle.fill")
        //         }
        // }
    }
}
