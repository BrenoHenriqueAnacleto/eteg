import { useEffect, useState } from "react";
import { ToastContainer } from "react-toastify";

export function ClientOnlyToast() {
  const [isClient, setIsClient] = useState(false);

  useEffect(() => {
    setIsClient(true);
  }, []);

  if (!isClient) return null;

  return <ToastContainer />;
}
