SELECT * FROM verse;

ALTER TABLE public.verse ALTER COLUMN "text" TYPE TEXT[] USING "text"::text[]


alter table commentary add primary key (id);

