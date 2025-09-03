import React, { useState, useEffect, useRef } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { Dialog } from "primereact/dialog";
import { InputText } from "primereact/inputtext";
import { Toast } from "primereact/toast";
import { ConfirmDialog, confirmDialog } from "primereact/confirmdialog";
import PerfilService from "../../services/PerfilService";

const Perfil = () => {
  const [perfis, setPerfis] = useState([]);
  const [perfil, setPerfil] = useState({ nome: "" });
  const [dialogVisible, setDialogVisible] = useState(false);
  const [loading, setLoading] = useState(true);
  const toast = useRef(null);
  
  const perfilService = new PerfilService();

  // Função para carregar os perfis da API
  const carregarPerfis = async () => {
    setLoading(true);
    try {
      const response = await perfilService.buscarTodos();
      setPerfis(response.data.content);
    } catch (error) {
      toast.current.show({
        severity: "error",
        summary: "Erro",
        detail: "Erro ao buscar os perfis!",
        life: 3000,
      });
    } finally {
      setLoading(false);
    }
  };

  // Carrega os dados apenas uma vez ao montar o componente
  useEffect(() => {
    carregarPerfis();
  }, []);

  // Funções de manipulação do estado e UI
  const abrirNovo = () => {
    setPerfil({ id: null, nome: "" });
    setDialogVisible(true);
  };

  const esconderDialog = () => {
    setDialogVisible(false);
  };

  const salvarPerfil = async () => {
    try {
      if (perfil.id) {
        await perfilService.alterar(perfil);
        toast.current.show({
          severity: "success",
          summary: "Atualizado",
          detail: "Perfil atualizado com sucesso!",
          life: 3000,
        });
      } else {
        await perfilService.inserir(perfil);
        toast.current.show({
          severity: "success",
          summary: "Criado",
          detail: "Perfil criado com sucesso!",
          life: 3000,
        });
      }
      carregarPerfis();
    } catch (error) {
      toast.current.show({
        severity: "error",
        summary: "Erro",
        detail: "Erro ao salvar perfil",
        life: 3000,
      });
    } finally {
      esconderDialog();
    }
  };

  const editarPerfil = (perfil) => {
    setPerfil({ ...perfil });
    setDialogVisible(true);
  };

  const confirmarExclusaoPerfil = (perfil) => {
    confirmDialog({
      message: `Tem certeza que deseja remover o perfil "${perfil.nome}"?`,
      header: "Confirmação",
      icon: "pi pi-exclamation-triangle",
      acceptLabel: "Sim",
      rejectLabel: "Não",
      accept: () => excluirPerfil(perfil),
    });
  };

  const excluirPerfil = async (perfil) => {
    try {
      await perfilService.excluir(perfil.id);
      toast.current.show({
        severity: "warn",
        summary: "Removido",
        detail: "Perfil removido com sucesso!",
        life: 3000,
      });
      carregarPerfis();
    } catch (error) {
      toast.current.show({
        severity: "error",
        summary: "Erro",
        detail: "Erro ao remover o perfil",
        life: 3000,
      });
    }
  };

  // Templates para a tabela
  const actionBodyTemplate = (rowData) => {
    return (
      <div className="flex gap-2">
        <Button
          icon="pi pi-pencil"
          rounded
          severity="success"
          onClick={() => editarPerfil(rowData)}
        />
        <Button
          icon="pi pi-trash"
          rounded
          severity="danger"
          onClick={() => confirmarExclusaoPerfil(rowData)}
        />
      </div>
    );
  };

  const dialogFooter = (
    <div className="flex gap-2 justify-end">
      <Button
        label="Cancelar"
        icon="pi pi-times"
        className="p-button-text"
        onClick={esconderDialog}
      />
      <Button
        label="Salvar"
        icon="pi pi-check"
        className="p-button-primary"
        onClick={salvarPerfil}
      />
    </div>
  );

  return (
    <div className="p-4 bg-gray-100 min-h-screen">
      <Toast ref={toast} />
      <ConfirmDialog acceptLabel="Sim" rejectLabel="Não" />

      <div className="bg-white rounded-lg shadow-md p-6">
        <div className="flex justify-between items-center mb-4">
          <h1 className="text-2xl font-bold text-gray-800">Gerenciar Perfis</h1>
          <Button
            label="Novo Perfil"
            icon="pi pi-plus"
            className="p-button-primary"
            onClick={abrirNovo}
          />
        </div>

        <DataTable
          value={perfis}
          loading={loading}
          paginator
          rows={10}
          rowsPerPageOptions={[5, 10, 25]}
          dataKey="id"
          emptyMessage="Nenhum perfil encontrado."
          className="shadow-lg"
        >
          <Column field="id" header="ID" sortable className="w-1/12" />
          <Column field="nome" header="Nome" sortable></Column>
          <Column
            header="Ações"
            body={actionBodyTemplate}
            exportable={false}
            className="w-1/12"
          ></Column>
        </DataTable>
      </div>

      <Dialog
        visible={dialogVisible}
        header={perfil.id ? "Editar Perfil" : "Novo Perfil"}
        modal
        className="w-full sm:w-1/2 md:w-1/3"
        footer={dialogFooter}
        onHide={esconderDialog}
      >
        <div className="flex flex-col gap-4">
          <div className="p-inputgroup flex-1">
            <span className="p-inputgroup-addon">
              <i className="pi pi-user"></i>
            </span>
            <InputText
              id="nome"
              name="nome"
              value={perfil.nome}
              onChange={(e) => setPerfil({ ...perfil, nome: e.target.value })}
              placeholder="Nome do Perfil"
              required
              autoFocus
              className="w-full"
            />
          </div>
        </div>
      </Dialog>
    </div>
  );
};

export default Perfil;
