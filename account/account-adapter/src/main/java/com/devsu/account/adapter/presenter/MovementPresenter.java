package com.devsu.account.adapter.presenter;

import java.util.List;
import java.util.stream.Collectors;

import com.devsu.account.adapter.viewmodel.MovementViewModel;
import com.devsu.account.dto.MovementResponse;

public class MovementPresenter {
    private List<MovementViewModel> viewModel;

    public void present(List<MovementResponse> responses) {
        this.viewModel = responses.stream()
                .map(res -> new MovementViewModel(
                        res.getId().toString(),
                        res.getAccountId().toString(),
                        res.getAmount(),
                        res.getCreationDate()))
                .collect(Collectors.toList());
    }

    public List<MovementViewModel> getViewModel() {
        return viewModel;
    }

}
