package br.com.fiap.medsave.ProjectMedSave.insfrastructure.config;

import lombok.Builder;

@Builder
public record JWTUserData(Long userId, String email) {
}
