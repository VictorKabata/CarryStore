//
//  ComposeView.swift
//  app-ios
//
//  Created by Victor Kabata on 02/08/2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import SwiftUI
import shared


struct ComposeView:UIViewControllerRepresentable{

    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {}

    func makeUIViewController(context: Context) -> some UIViewController {
        MainViewControllerKt.MainViewController()
    }

}
