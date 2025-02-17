import { useClienteForm } from "~/hooks/useClienteForm";
import axios, {type AxiosError} from "axios";
import {toast} from "react-toastify";

export default function CadastroCliente() {

  const { form, coresPermitidas, schema } = useClienteForm();
  const { register, handleSubmit, formState: { errors }, reset } = form;

  const onSubmit = async (data: any) => {
    try {
      await axios.post("http://localhost:8080/v1/cadastro", data);
      toast.success("Cadastro realizado com sucesso!", {
        position: "top-right",
        autoClose: 3000, // Fecha após 3 segundos
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        theme: "light",
      });
      reset();
    } catch (error: any) {
      console.log(error.response.data)
      toast.error(error.response.data.descricao, {
        position: "top-right",
        autoClose: 3000, // Fecha após 3 segundos
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        theme: "light",
      });
    }
  };

  if (!schema) return <p>Carregando...</p>;

  return (
    <div className="max-w-md mx-auto mt-10 p-6 rounded-lg shadow-md">
      <h2 className="text-2xl font-bold text-center mb-6">Cadastro de Cliente</h2>
      <form onSubmit={handleSubmit(onSubmit)} className="space-y-4">
        {/* Nome */}
        <div>
          <label className="block font-medium">Nome Completo</label>
          <input {...register("nome")} className="w-full border p-2 rounded" />
          {errors.nome && <p className="text-red-500">{errors.nome.message}</p>}
        </div>

        {/* CPF */}
        <div>
          <label className="block font-medium">CPF</label>
          <input {...register("cpf")} className="w-full border p-2 rounded" placeholder="000.000.000-00" />
          {errors.cpf && <p className="text-red-500">{errors.cpf.message}</p>}
        </div>

        {/* Email */}
        <div>
          <label className="block font-medium">E-mail</label>
          <input {...register("email")} type="email" className="w-full border p-2 rounded" />
          {errors.email && <p className="text-red-500">{errors.email.message}</p>}
        </div>

        {/* Cor Preferida */}
        <div>
          <label className="block font-medium">Cor Preferida</label>
          <select {...register("corId")} className="w-full border p-2 rounded">
            {coresPermitidas.map((cor) => (
              <option className="text-black" key={cor.id} value={cor.id}>
                {cor.nome}
              </option>
            ))}
          </select>
          {errors.corId && <p className="text-red-500">{errors.corId.message}</p>}
        </div>

        {/* Observações */}
        <div>
          <label className="block font-medium">Observações</label>
          <textarea {...register("observacoes")} className="w-full border p-2 rounded" />
          {errors.observacoes && <p className="text-red-500">{errors.observacoes.message}</p>}
        </div>

        {/* Botão de Enviar */}
        <button type="submit" className="w-full bg-blue-500 text-white p-2 rounded hover:bg-blue-600">
          Cadastrar
        </button>
      </form>
    </div>
  );
}
