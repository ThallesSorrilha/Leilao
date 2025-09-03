import { useState, useEffect, useRef } from 'react';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { Button } from 'primereact/button';
import { Dialog } from 'primereact/dialog';
import { InputText } from 'primereact/inputtext';
import { InputTextarea } from 'primereact/inputtextarea';
import { Toast } from 'primereact/toast';
import { ConfirmDialog, confirmDialog } from 'primereact/confirmdialog';

const CategoriaCRUD = () => {
    const [categorias, setCategorias] = useState([]);
    const [loading, setLoading] = useState(false);
    const [dialogVisible, setDialogVisible] = useState(false);
    const [categoria, setCategoria] = useState({ id: null, nome: '', observacao: '' });
    const [submitted, setSubmitted] = useState(false);
    const toast = useRef(null);

    // Fetch data from the backend
    const fetchCategorias = async () => {
        setLoading(true);
        try {
            const response = await fetch('http://localhost:8080/categoria');
            if (!response.ok) {
                throw new Error('Erro ao buscar categorias.');
            }
            const data = await response.json();
            setCategorias(data.content); // Use data.content for paginated response
        } catch (error) {
            toast.current.show({
                severity: 'error',
                summary: 'Erro',
                detail: error.message,
                life: 3000,
            });
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchCategorias();
    }, []);

    // API calls
    const saveCategoria = async () => {
        setSubmitted(true);
        if (categoria.nome.trim()) {
            const url = `http://localhost:8080/categoria`;
            const method = categoria.id ? 'PUT' : 'POST';

            try {
                const response = await fetch(url, {
                    method: method,
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(categoria),
                });

                if (!response.ok) {
                    throw new Error(`Erro ao ${categoria.id ? 'alterar' : 'inserir'} categoria.`);
                }

                await fetchCategorias(); // Refresh data
                setDialogVisible(false);
                toast.current.show({
                    severity: 'success',
                    summary: 'Sucesso',
                    detail: `Categoria ${categoria.id ? 'alterada' : 'criada'} com sucesso!`,
                    life: 3000,
                });
            } catch (error) {
                toast.current.show({
                    severity: 'error',
                    summary: 'Erro',
                    detail: error.message,
                    life: 3000,
                });
            }
        }
    };

    const deleteCategoria = async (id) => {
        try {
            const response = await fetch(`http://localhost:8080/categoria/${id}`, {
                method: 'DELETE',
            });

            if (!response.ok) {
                throw new Error('Erro ao excluir categoria.');
            }

            await fetchCategorias(); // Refresh data
            toast.current.show({
                severity: 'success',
                summary: 'Sucesso',
                detail: 'Categoria excluída com sucesso!',
                life: 3000,
            });
        } catch (error) {
            toast.current.show({
                severity: 'error',
                summary: 'Erro',
                detail: error.message,
                life: 3000,
            });
        }
    };

    // UI actions
    const openNew = () => {
        setCategoria({ id: null, nome: '', observacao: '' });
        setSubmitted(false);
        setDialogVisible(true);
    };

    const editCategoria = (data) => {
        setCategoria({ ...data });
        setDialogVisible(true);
    };

    const hideDialog = () => {
        setDialogVisible(false);
        setSubmitted(false);
    };

    const confirmDelete = (data) => {
        confirmDialog({
            message: `Tem certeza que deseja excluir a categoria "${data.nome}"?`,
            header: 'Confirmação',
            icon: 'pi pi-exclamation-triangle',
            acceptLabel: 'Sim',
            rejectLabel: 'Não',
            accept: () => deleteCategoria(data.id),
        });
    };

    const onInputChange = (e) => {
        const { name, value } = e.target;
        setCategoria((prev) => ({ ...prev, [name]: value }));
    };

    const actionBodyTemplate = (rowData) => {
        return (
            <div className="flex gap-2">
                <Button
                    icon="pi pi-pencil"
                    rounded
                    severity="success"
                    onClick={() => editCategoria(rowData)}
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
            <Button
                label="Salvar"
                icon="pi pi-check"
                onClick={saveCategoria}
            />
        </div>
    );

    return (
        <div className="p-4 bg-gray-100 min-h-screen">
            <Toast ref={toast} />
            <ConfirmDialog />
            <div className="bg-white rounded-lg shadow-md p-6">
                <div className="flex justify-between items-center mb-4">
                    <h1 className="text-2xl font-bold text-gray-800">Gerenciar Categorias</h1>
                    <Button
                        label="Nova Categoria"
                        icon="pi pi-plus"
                        onClick={openNew}
                        className="p-button-primary"
                    />
                </div>
                <DataTable
                    value={categorias}
                    paginator
                    rows={10}
                    loading={loading}
                    dataKey="id"
                    emptyMessage="Nenhuma categoria encontrada."
                    className="shadow-lg"
                >
                    <Column field="id" header="ID" sortable className="w-1/12" />
                    <Column field="nome" header="Nome" sortable />
                    <Column field="observacao" header="Observação" sortable />
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
                header={categoria.id ? 'Editar Categoria' : 'Nova Categoria'}
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
                            id="nome"
                            name="nome"
                            value={categoria.nome}
                            onChange={onInputChange}
                            placeholder="Nome da Categoria"
                            required
                            autoFocus
                            className={`w-full ${submitted && !categoria.nome.trim() ? 'p-invalid' : ''}`}
                        />
                    </div>
                    {submitted && !categoria.nome.trim() && (
                        <small className="p-error text-red-500">Nome é obrigatório.</small>
                    )}

                    <div className="p-inputgroup flex-1">
                        <span className="p-inputgroup-addon">
                            <i className="pi pi-comment"></i>
                        </span>
                        <InputTextarea
                            id="observacao"
                            name="observacao"
                            value={categoria.observacao}
                            onChange={onInputChange}
                            placeholder="Observação"
                            rows={3}
                            autoResize
                            className="w-full"
                        />
                    </div>
                </div>
            </Dialog>
        </div>
    );
};

export default CategoriaCRUD;
