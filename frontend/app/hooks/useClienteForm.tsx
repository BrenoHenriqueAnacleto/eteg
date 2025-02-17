import { useEffect, useState } from "react";
import { useForm } from "react-hook-form";
import { zodResolver } from "@hookform/resolvers/zod";
import { z } from "zod";

async function buscarCoresDoBackend(): Promise<Cor[]> {
  try {
    const resposta = await fetch("http://localhost:8080/v1/cores");
    if (!resposta.ok) throw new Error("Erro ao buscar cores");
    return await resposta.json();
  } catch (error) {
    console.error(error);
    return [];
  }
}

const validarCPF = (cpf: string) => /^\d{3}\.\d{3}\.\d{3}-\d{2}$/.test(cpf);
interface Cor {
  id: number;
  nome: string;
}

export function useClienteForm() {
  const [coresPermitidas, setCoresPermitidas] = useState<Cor[]>([]);
  const [schema, setSchema] = useState<z.ZodObject<any> | null>(null);

  useEffect(() => {
    async function carregarCores() {
      const cores = await buscarCoresDoBackend();
      setCoresPermitidas(cores);

      const novoSchema = z.object({
        nome: z.string().min(3, "O nome deve ter pelo menos 3 caracteres"),
        cpf: z.string().refine(validarCPF, "CPF inválido! Formato: 000.000.000-00"),
        email: z.string().email("E-mail inválido"),
        corId: z.string().refine(
          (corId) => {
            const valid = cores.some(cor => cor.id === Number(corId));
            return valid;
          },
          "Cor inválida! Escolha uma das opções disponíveis."
        ),
        observacoes: z.string().max(500, "Máximo de 500 caracteres").optional(),
      });

      setSchema(novoSchema);
    }

    carregarCores();
  }, []);

  const form = useForm({
    resolver: schema ? zodResolver(schema) : undefined,
    defaultValues: {
      nome: "",
      cpf: "",
      email: "",
      corId: coresPermitidas.length > 0 ? coresPermitidas[0].id.toString() : "",
      observacoes: "",
    },
  });

  useEffect(() => {
    if (coresPermitidas.length > 0) {
      form.setValue("corId", coresPermitidas[0].id.toString());
    }
  }, [coresPermitidas, form]);

  return { form, coresPermitidas, schema };
}
