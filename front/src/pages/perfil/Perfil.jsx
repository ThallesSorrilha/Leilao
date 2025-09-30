import { useState, useEffect, useRef } from "react";
import { DataTable } from "primereact/datatable";
import { Column } from "primereact/column";
import { Button } from "primereact/button";
import { Dialog } from "primereact/dialog";
import { InputText } from "primereact/inputtext";
import { Toast } from "primereact/toast";
import { ConfirmDialog, confirmDialog } from "primereact/confirmdialog";
import PerfilService from "../../services/PerfilService";

const PerfilCRUD = () => {
  const [perfis, setPerfis] = useState([]);
  const [loading, setLoading] = useState(false);
  const [dialogVisible, setDialogVisible] = useState(false);
  const [perfil, setPerfil] = useState({
    id: null,
    tipo: "",
  });
  const [submitted, setSubmitted] = useState(false);
  const toast = useRef(null);
  const perfilService = new PerfilService();

  const fetchPerfis = async () => {
    setLoading(true);
    try {
      const response = await perfilService.buscarTodos();
      if (response && response.data && Array.isArray(response.data.content)) {
        setPerfis(response.data.content);
      } else {
        setPerfis([]);
        console.warn(
          "Resposta da API de perfis em formato inesperado ou vazia:",
          response.data
        );
      }
    } catch (error) {
      toast.current.show({
        severity: "error",
        summary: "Erro",
        detail: error.message,
        life: 3000,
      });
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchPerfis();
  }, []);

  const savePerfil = async () => {
    setSubmitted(true);
    if (perfil.tipo.trim()) {
      const action = perfil.id
        ? perfilService.alterar.bind(perfilService)
        : perfilService.inserir.bind(perfilService);
      try {
        const response = await action(perfil);
        await fetchPerfis();
        setDialogVisible(false);
        toast.current.show({
          severity: "success",
          summary: "Sucesso",
          detail: `Perfil ${perfil.id ? "alterada" : "criada"} com sucesso!`,
          life: 3000,
        });
      } catch (error) {
        toast.current.show({
          severity: "error",
          summary: "Erro",
          detail: error.message,
          life: 3000,
        });
      }
    }
  };

  const deletePerfil = async (id) => {
    try {
      const response = await perfilService.excluir(id);
      await fetchPerfis();
      toast.current.show({
        severity: "success",
        summary: "Sucesso",
        detail: "Perfil excluída com sucesso!",
        life: 3000,
      });
    } catch (error) {
      toast.current.show({
        severity: "error",
        summary: "Erro",
        detail: error.message,
        life: 3000,
      });
    }
  };

  const openNew = () => {
    setPerfil({ id: null, tipo: "" });
    setSubmitted(false);
    setDialogVisible(true);
  };

  const editPerfil = (data) => {
    setPerfil({ ...data });
    setDialogVisible(true);
  };

  const hideDialog = () => {
    setDialogVisible(false);
    setSubmitted(false);
  };

  const confirmDelete = (data) => {
    confirmDialog({
      message: `Tem certeza que deseja excluir a perfil "${data.tipo}"?`,
      header: "Confirmação",
      icon: "pi pi-exclamation-triangle",
      acceptLabel: "Sim",
      rejectLabel: "Não",
      accept: () => deletePerfil(data.id),
    });
  };

  const onInputChange = (e) => {
    const { name, value } = e.target;
    setPerfil((prev) => ({ ...prev, [name]: value }));
  };

  const actionBodyTemplate = (rowData) => {
    return (
      <div className="flex gap-2">
        <Button
          icon="pi pi-pencil"
          rounded
          severity="success"
          onClick={() => editPerfil(rowData)}
        />
        <Button
          icon="pi pi-trash"
          rounded
          severity="danger"
          onClick={() => confirmDelete(rowData)}
        />
      </div>
    );
  };

  const dialogFooter = (
    <div className="flex gap-2">
      <Button
        label="Cancelar"
        icon="pi pi-times"
        className="p-button-text"
        onClick={hideDialog}
      />
      <Button label="Salvar" icon="pi pi-check" onClick={savePerfil} />
    </div>
  );

  return (
    <div className="p-4 bg-gray-100 min-h-screen">
      <Toast ref={toast} />
      <ConfirmDialog />
      <div className="bg-white rounded-lg shadow-md p-6">
        <div className="flex justify-between items-center mb-4">
          <h1 className="text-2xl font-bold text-gray-800">Gerenciar Perfis</h1>
          <Button
            label="Nova Perfil"
            icon="pi pi-plus"
            onClick={openNew}
            className="p-button-primary"
          />
        </div>
        <DataTable
          value={perfis}
          paginator
          rows={10}
          loading={loading}
          dataKey="id"
          emptyMessage="Nenhuma perfil encontrada."
          className="shadow-lg"
        >
          <Column field="id" header="ID" sortable className="w-1/12" />
          <Column field="tipo" header="Tipo" sortable />
          <Column
            header="Ações"
            body={actionBodyTemplate}
            exportable={false}
            className="w-1/12"
          />
        </DataTable>
      </div>

      <Dialog
        visible={dialogVisible}
        header={perfil.id ? "Editar Perfil" : "Nova Perfil"}
        modal
        className="w-full sm:w-1/2 md:w-1/3"
        footer={dialogFooter}
        onHide={hideDialog}
      >
        <div className="flex flex-col gap-4">
          <div className="p-inputgroup flex-1">
            <span className="p-inputgroup-addon">
              <i className="pi pi-tag"></i>
            </span>
            <InputText
              id="tipo"
              name="tipo"
              value={perfil.tipo}
              onChange={onInputChange}
              placeholder="Tipo do Perfil"
              required
              autoFocus
              className={`w-full ${
                submitted && !perfil.tipo.trim() ? "p-invalid" : ""
              }`}
            />
          </div>
        </div>
      </Dialog>
    </div>
  );
};

export default PerfilCRUD;
