-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
DROP DATABASE IF EXISTS Oficina;
CREATE DATABASE Oficina;
USE Oficina;
    
set sql_safe_updates = 0; 

-- -----------------------------------------------------
-- Table `mydb`.`Mecanico`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Oficina.Mecanico (
  cpf VARCHAR(14) NOT NULL,
  nome VARCHAR(30) NOT NULL,
  PRIMARY KEY (cpf)
);

-- -----------------------------------------------------
-- Table `mydb`.`Dono`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Oficina.Dono (
  cpf VARCHAR(14) NOT NULL,
  nome VARCHAR(30) NOT NULL,
  PRIMARY KEY (cpf)
);

-- -----------------------------------------------------
-- Table `mydb`.`Documento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Oficina.Documento (
  renavam INT NOT NULL,
  anoDoVeiculo INT NOT NULL,
  PRIMARY KEY (renavam)
);
-- ----------------------------------------------------
-- Table `mydb`.`Carro`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Oficina.Carro (
  numeroChassi INT NOT NULL,
  cor VARCHAR(10) NOT NULL,
  modelo VARCHAR(20) NOT NULL,
  Dono_cpf VARCHAR(14) NOT NULL,
  Mecanico_cpf VARCHAR(14) NOT NULL,
  Documento_renavam INT NOT NULL,
  PRIMARY KEY (numeroChassi, Dono_cpf),
  
  CONSTRAINT fk_Carro_Dono1
    FOREIGN KEY (Dono_cpf)
    REFERENCES Oficina.Dono (cpf)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    
  CONSTRAINT fk_Carro_Mecanico1
    FOREIGN KEY (Mecanico_cpf)
    REFERENCES Oficina.Mecanico (cpf)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    
  CONSTRAINT fk_Carro_Documento1
	FOREIGN KEY (Documento_renavam)
    REFERENCES Oficina.Documento (renavam)
    ON DELETE CASCADE
    ON UPDATE CASCADE
);

-- -----------------------------------------------------
-- Table `mydb`.`Manutencao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Oficina.Manutencao (
  idManutencao INT NOT NULL auto_increment,
  status VARCHAR(45) NOT NULL DEFAULT 'Em andamento',
  problema VARCHAR(45) NOT NULL,
  PRIMARY KEY (idManutencao)
);

-- -----------------------------------------------------
-- Table `mydb`.`Carro_has_Manutencao`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Oficina.Carro_has_Manutencao (
  Carro_numeroChassi INT NOT NULL,
  Manutencao_idManutencao INT NOT NULL,
  PRIMARY KEY (Carro_numeroChassi, Manutencao_idManutencao),
  
  CONSTRAINT fk_Carro_has_Manutencao_Carro
    FOREIGN KEY (Carro_numeroChassi)
    REFERENCES Oficina.Carro (numeroChassi)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
 
 CONSTRAINT fk_Carro_has_Manutencao_Manutencao1
    FOREIGN KEY (Manutencao_idManutencao)
    REFERENCES Oficina.Manutencao (idManutencao)
    ON DELETE CASCADE
    ON UPDATE CASCADE
); 