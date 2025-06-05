
ALTER TABLE usuario
  ADD COLUMN uuid UUID;

ALTER TABLE perito
    ADD COLUMN uuid UUID;

ALTER TABLE gerente_regional
    ADD COLUMN uuid UUID;

ALTER TABLE administrador
    ADD COLUMN uuid UUID;

