package com.jumparoundcreations.toolbox.di

import com.jumparoundcreations.toolbox.paceCalculator.PaceCalculatorViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val platformModule =
    module {
        this.viewModel {
            PaceCalculatorViewModel()
        }
    }