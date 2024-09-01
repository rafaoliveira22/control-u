export interface SelectProps {
  value: number | string;
  onChange: (event: React.ChangeEvent<{ value: unknown }>) => void;
}