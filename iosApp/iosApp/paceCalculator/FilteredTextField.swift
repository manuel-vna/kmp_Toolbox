//
// Created by Manuel von Au on 12.10.26.
//

import Foundation
import SwiftUI

struct FilteredTextField: View {
    let title: String
    let value: String
    let keyboardType: UIKeyboardType
    var isFocused: FocusState<Bool>.Binding
    let isValid: (String) -> Bool
    let propagateAction: (String) -> Void

    @State private var text: String = ""

    var body: some View {
        TextField(title, text: $text)
        .multilineTextAlignment(.center)
        .frame(width: 80)
        .frame(maxWidth: .infinity, alignment: .center)
        .keyboardType(keyboardType)
        .focused(isFocused)
        .onAppear {
            text = value
        }
        .onChange(of: value) { oldValue, externalValue in
            if text != externalValue {
                text = externalValue
            }
        }
        .onChange(of: text) { oldValue, newValue in
            if isValid(newValue) {
                if newValue != value {
                    propagateAction(newValue)
                }
            } else {
                text = value
            }
        }
    }
}
