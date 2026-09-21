//
// Created by Manuel von Au on 20.09.26.
//

import SwiftUI
import SharedLogic
import Foundation

struct PaceCalculatorView: View {
    let state: PaceCalculatorStates
    let onAction: (PaceCalculatorIntents) -> Void

    var body: some View {
        Form {
            Section(header: Text("Parameters")) {
                HStack {
                    Text("Distance")
                    Spacer()
                    TextField("0.0", text: Binding(
                        get: { state.distance == 0 ? "" : String(state.distance) },
                        set: { newValue in
                            if let newDistance = Double(newValue) {
                                onAction(PaceCalculatorIntents.ChangeDistance(newDistance: newDistance))
                            }
                        }
                    ))
                    .keyboardType(.decimalPad)
                    .multilineTextAlignment(.trailing)
                    Text("km")
                }
            }
        }
    }
}
