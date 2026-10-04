//
//  Dashboard.swift
//  iosApp
//
//  Created by Manuel von Au on 04.10.26.
//

import SwiftUI

struct Dashboard: View {
    var body: some View {
        VStack(spacing: 16) {
            NavigationLink("Pace Calculator", value: AppRoute.paceCalculator)
                .buttonStyle(.borderedProminent)

            NavigationLink("Unknown", value: AppRoute.unknown)
                .buttonStyle(.borderedProminent)
        }
        .padding()
        .navigationTitle("Dashboard")
    }
}

struct Dashboard_Previews: PreviewProvider {
    static var previews: some View {
        NavigationStack {
            Dashboard()
        }
    }
}
